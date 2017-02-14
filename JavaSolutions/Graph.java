//Thomas Fahrner

import java.util.*;
import java.io.*;

public class Graph
{
    private String fileName;
    private int numRows;
    private int numCols;
    private int[][] maze;
    private int[][] adj;
    private int[] from;
    private int[] cost;
    private ArrayList<Integer> path;

    public Graph(String initFile) throws FileNotFoundException, IOException
    {
        fileName = initFile;
        BufferedReader in = new BufferedReader(new FileReader(fileName));

        String[] header = in.readLine().split(" ");
        numRows = Integer.parseInt(header[0]);
        numCols = Integer.parseInt(header[header.length - 1]);

        maze = new int[numRows][numCols];
        adj = new int[numRows*numCols][numRows*numCols];

        //build maze, -1: black, 0: white
        for(int i = 0; i < numRows; i++)
        {
            //parse ints removing whitespace
            String[] line = in.readLine().split("\\s+");
        
            for(int j = 0; j < numCols; j++)
            {
                maze[i][j] = Integer.parseInt(line[j]);
            }    
        }    

        buildAdj();
    }

    private void buildAdj()
    {
        //0: not a neighbor, 1: neighbor
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {
                //if current location is black then all neighbors set to 0 and continue
                if(maze[i][j] == -1)
                {
                    continue;
                }

                int n = i*numCols + j;

                //check left neighbor
                if(j - 1 >= 0)
                {
                    if(maze[i][j-1] == 0)
                    {
                        adj[n][n-1] = 1;
                        adj[n-1][n] = 1;
                    }
                    else
                    {
                        adj[n][n-1] = 0;
                        adj[n-1][n] = 0;
                    }
                }

                //check up neighbor
                if(i - 1 >= 0)
                {
                    if(maze[i-1][j] == 0)
                    {
                        adj[n][n-numCols] = 1;
                        adj[n-numCols][n] = 1;
                    }
                    else
                    {
                        adj[n][n-numCols] = 0;
                        adj[n-numCols][n] = 0;
                    }
                }

                //check right neighbor
                if(j + 1 < numCols)
                {
                    if(maze[i][j+1] == 0)
                    {
                        adj[n][n+1] = 1;
                        adj[n+1][n] = 1;
                    }
                    else
                    {
                        adj[n][n+1] = 0;
                        adj[n+1][n] = 0;
                    }
                }

                //check down neighbor
                if(i + 1 < numRows)
                {
                    if(maze[i+1][j] == 0)
                    {
                        adj[n][n+numCols] = 1;
                        adj[n+numCols][n] = 1;
                    }
                    else
                    {
                        adj[n][n+numCols] = 0;
                        adj[n+numCols][n] = 0;
                    }
                }
            }
        }
    }

    public int shortestPath(int start, int stop)
    {
        if(start > (numRows - 1)*numCols + numCols || stop > (numRows - 1)*numCols + numCols || start < 0 || stop < 0) {return -1;}

        if(start == stop){return 0;}

        from = new int[numRows*numCols];
        cost = new int[numRows*numCols];
        Arrays.fill(from, -1);
        Arrays.fill(cost, -1);

        Queue<Integer> q = new LinkedList<Integer>();

        cost[start] = 0;
        q.add(start);

        while(!(q.isEmpty()))
        {
            int v = q.poll();

            for(int i = 0; i < (numRows*numCols); i++)
            {
                if(adj[v][i] != 0 && cost[i] == -1)
                {
                    from[i] = v;
                    cost[i] = cost[v] + 1;
                    q.add(i);
                }
            }
        }

        path = new ArrayList<Integer>();

        int backtrack = stop;
        while(backtrack != -1)
        {
            path.add(backtrack);
            backtrack = from[backtrack];
        }
        
        return cost[stop];
    }

    public ArrayList<Integer> path()
    {
        return path;
    }

    public int[][] maze()
    {
        return maze;
    }

    public int[][] adj()
    {
        return adj;
    }

    public int numRows()
    {
        return numRows;
    }

    public int numCols()
    {
        return numCols;
    }

    public void darken(int index)
    {
        int row = index / numCols;
        int col = index % numCols;

        //update maze
        maze[row][col] = -1;
        buildAdj();
    }

    /*
    public void printMaze()
    {
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numCols; j++)
            {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printAdj()
    {
        for(int i = 0; i < numRows*numCols; i++)
        {
            for(int j = 0; j < numRows*numCols; j++)
            {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }
    */
}
