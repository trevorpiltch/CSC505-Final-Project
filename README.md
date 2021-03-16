# Trevor and Eric's CSC-505 Design Project
## Description
This project creates a tree based on the frequency of character in a string. The more frequent the character the closer it is to the root. The string is encoded into binary code (1’s and 0’s) that corresponds to 1: go right of node and 0: go left of node. We also included various methods which are listed with their functions below. This website ([https://people.ok.ubc.ca/ylucet/DS/Huffman.html], disclaimer that this was **NOT** created by us, shows a visual representation of what the HuffmanTree is doing. 

## Instructions
To utilize this project, download the zip file and open the tester class. The other two classes (HTNode and HT) should not be modified. In the tester class you can modify the string value to any string you want to encode using the HuffmanTree. Be sure that the value is only of type string and to view the difference between your original value and the HuffmanCode compressed string. 

## Methods
### Initializer
Sets the root to null and builds the tree

### Build Tree
Creates the tree using a priority queue which automatically sorts by value. The tree is created by combining the two least frequent nodes into a tree (with a parent and value of the combined child frequencies). This process continues until there is one tree left in the queue and that becomes the Huffman Tree. 

### Encode 
Creates a HuffmanCode (1’s and 0’s) that corresponds to the input encoded into the tree. A 1 means go right in the tree and a 0 means go left. 

### Decode
Creates a string based on the given HuffmanCode. Again a 0 means go left and a 1 means go right. This continues until it reaches a leaf node and then adds the leaf value to the output. Repeats until the HuffmanCode is empty.

### Find Frequency
Returns a map with the number of times the character passed in occurs in the string also passed in and the character itself. 

### Find Frequencies
Returns a list of maps that contain a character and its respective frequency. 

### InOrder
Prints the values in the tree from left to right. 

### Compare Storage
Returns the percent difference between the original value string and the encoded value string. 
