# Text Generator
Generates simulated text based on training text

### Usage

The shell script run.sh compiles the java files into the *bin* folder, runs the compiled files, and removes them, so all that needs to be done in order to run this program is to enter `./run.sh` in the main project directory. When prompted for a path to training data, the user can provide their own or use one of provided files, `training-data/Constitution.txt`, which is the full text of the United States Constitution, or `training-data/Metamorphosis.txt`, which is the full text of Franz Kafka's *Metamorphosis*. The nGram length is the number of training characters that is used to determine each successive character. Longer nGrams result in text that more exactly matches the training text, while shorter text could lead to incomprehensible words. It is recommended to explore nGram lengths between 3 and 8 characters. Next, the user will be prompted to enter the length of the output text, and then output text from two different implementations of text generation will be printed on the screen.

### Detail

#### ArrayList Implementation

The first of the two implementations is based on a simple ArrayList of characters created for each nGram the program writes. For each nGram, program will go through the entire training text, and each time it encounters the current nGram, add the next occuring character to the ArrayList. This way, a random character can be chosen from the list with equal probability of picking one from the book (assuming it is following the nGram). This process is repeated until the number of characters picked matches the chosen output length.

#### ArrayOfListsMap Implementation

The second of the two implementations is based on a map with nGram keys and ArrayLists of characters for the values. For each character in the training text, the program will add the character to its preceding nGram's ArrayList in the map. Then, output characters can be chosen in much the same way from the corresponging ArrayList to each nGram. However, this is not just a simple map. The map is custom implemented as a hash table. The table is structured as an array of LinkedLists, with each LinkedList corresponding to a result of the hash function. The LinkedLists themselves contain HashNodes with the hashed nGram (in this usage) as the key and the ArrayList of characters (in this usage) as the value.
