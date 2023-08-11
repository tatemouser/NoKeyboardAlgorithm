# NoKeyboardAlgorithm

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)
- [Contact](#contact)

## Project Description
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; The concept of this project is to create an algorithm for a keyboard with only 10 buttons. Each button, as seen below, represents a few letters on the keyboard. This project generates several combinations of words that could be created with numeric input, then selects the words that exist in the program's word bank. Each word in the sentence is linked to a list of these valid combinations. The valid combinations are then run through a bigram language model with the preceding word list. This process creates another list of scores associated with all the combinations between the two lists holding possible words. The best score is used, and the two words that achieved this score are used in the final sentence output.

<pre>The digit 1 represents "a". This word would be added to a list, then it would be searched for in the trie structured word bank. Since "a" is found then it would be returned.
By entering 73999, the ideal output would be "hello". Although it would produce several variations of this. Here is an example of the letter combination size.</pre>
> * Level 1: 6 options (Y, U, H, J, N, M)
> * Level 2: 3 options for each of the 6 options from Level 1 = 6 * 3 = 18 options (E, D, C)
> * Level 3: 3 options for each of the 18 options from Level 2 = 18 * 2 = 36 options (O, L)
> * Level 4: 3 options for each of the 54 options from Level 3 = 54 * 2 = 72 options (O, L)
> * Level 5: 3 options for each of the 162 options from Level 4 = 162 * 2 = 144 options (O, L)   
> 1 of the 144 will be hello with the correct letters. Once a combination is found in the trie, it will be returned.

<img src="https://github.com/tatemouser/NoKeyboardAlgorithm/assets/114375692/a4a03227-d414-4729-b8a6-caeb95af825c" alt="keyboardImage" width="500" height="200">

<pre> If "7970317" is entered. NumToWord finds word combinations for 797 and 317, then operations will perform as seen below. </pre>
![diagramBigram](https://github.com/tatemouser/NoKeyboardAlgorithm/assets/114375692/b5934545-0fe2-49b3-9ad6-bab022a67735)



## Features
- Dictionary stored in Trie data structure. (25,000 words)
- Word to number and number to word algorithm.
- Bigram language model used to get relational score between two words.
- Enhanced prediction, comparing multiple iterations of scores from pairwise combinations.
- Writing over CSV file.
  
## Installation

## Usage

## Contributing

## License
None.

## Acknowledgments

## Contact
tatesmouser@gmail.com
