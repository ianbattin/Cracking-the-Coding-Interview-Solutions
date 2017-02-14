def unique_chars(string):
	charArray
	for stringChar in range(0, len(string)):
		char = ord(string[stringChar])
		if(charArray[char] == 1):
			return false
		
		charArray[char] += 1
	return true
	
print(unique_chars("This"))