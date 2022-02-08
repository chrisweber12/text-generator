# Text Generator
Generates simulated text based on training text

### Usage

The shell script run.sh compiles the java files into the *bin* folder, runs the compiled files, and removes them, so all that needs to be done in order to run this program is to enter `./run.sh` in the main project directory. When prompted for a path to training data, the user can provide their own or use one of provided files, *training-data/Constitution.txt, which is the full text of the United States Constitution, or *training-data/Metamorphosis.txt* which is the full text of Franz Kafka's *Metamorphosis*. The nGram length is the number of training characters that is used to determine each successive character. Longer nGrams result in text that more exactly matches the training text, while shorter text could lead to incomprehensible words. It is recommended to explore nGram lengths between 3 and 8 characters. Next, the user will be prompted to enter the length of the output text, and then output text from two different implementations of text generation will be printed on the screen.
