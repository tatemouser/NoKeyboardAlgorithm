# NoKeyboardAlgorithm

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Acknowledgments](#acknowledgments)
- [Contact](#contact)

## Project Description
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; The concept of this project is to create an algorithm for a keyboard with only 10 buttons. Each button, as seen below, represents a few letters on the keyboard. This project generates several combinations of words that could be created with numeric input, then selects the words that exist in the program's word bank. Each word in the sentence is linked to a list of these valid combinations. The valid combinations are then run through a bigram language model with the preceding word list. This process creates another list of scores associated with all the combinations between the two lists holding possible words. The best score is used, and the two words that achieved this score are used in the final sentence output.

The digit 1 represents "a". This word would be added to a list, then it would be searched for in the trie structured word bank. Since "a" is found then it would be returned.

By entering 73999, the ideal output would be "hello". Although it would produce several variations of this. Here is an example of the letter combination size.
> * Level 1: 6 options (Y, U, H, J, N, M)
> * Level 2: 3 options for each of the 6 options from Level 1 = 6 * 3 = 18 options (E, D, C)
> * Level 3: 3 options for each of the 18 options from Level 2 = 18 * 2 = 36 options (O, L)
> * Level 4: 3 options for each of the 54 options from Level 3 = 54 * 2 = 72 options (O, L)
> * Level 5: 3 options for each of the 162 options from Level 4 = 162 * 2 = 144 options (O, L)   
> 1 of the 144 will be hello with the correct letters. Once a combination is found in the trie, it will be returned.

<img src="https://github.com/tatemouser/NoKeyboardAlgorithm/assets/114375692/a4a03227-d414-4729-b8a6-caeb95af825c" alt="keyboardImage" width="500" height="200">

#### User Interface

<div style="display: flex; justify-content: space-between;">
    <img src="https://raw.githubusercontent.com/tatemouser/NoKeyboardAlgorithm/main/SmallKeyboard/images/UIOne.png" alt="keyboardImage" width="500" height="300">
    <img src="https://raw.githubusercontent.com/tatemouser/NoKeyboardAlgorithm/main/SmallKeyboard/images/UIShow.png" alt="keyboardImage" width="500" height="300">
</div>


##### Further Conversion Explanation
If "7970317" is entered. NumToWord finds word combinations for 797 and 317, then operations will perform as seen below. 

![diagramBigram](https://github.com/tatemouser/NoKeyboardAlgorithm/assets/114375692/b5934545-0fe2-49b3-9ad6-bab022a67735)



## Features
- Dictionary stored in Trie data structure. (25,000 words)
- Word to number and number to word algorithm.
- User interface built with standard widget toolkit
- Bigram language model used to get relational score between two words.
- Enhanced prediction, comparing multiple iterations of scores from pairwise combinations.
- Writing over CSV file.
  
## Installation
    git clone https://github.com/tatemouser/NoKeyboardAlgorithm.git

## Usage
#### Packages:

<pre>
main:
&nbsp;&nbsp;&nbsp;Initializes program.
&nbsp;&nbsp;&nbsp;Converts numbers to words and words to numbers. 
&nbsp;&nbsp;&nbsp;LinkingMatchesAndBigram calls BigramMain with combinations to get best scores. </pre>

<pre>
sorting:
&nbsp;&nbsp;&nbsp;Creates trie to hold dictionary and generated words. Used for adding or removing words. </pre>

<pre>
bigramLanguageModel:
&nbsp;&nbsp;&nbsp;BigramLanguageModel trains and builds the data structure.
&nbsp;&nbsp;&nbsp;BigramMain relays information from the data structure. </pre>

<pre>
display:
&nbsp;&nbsp;&nbsp;UIWindow creates window and links main conversion to update display 
&nbsp;&nbsp;&nbsp;Other classes represent regions of window named accordingly </pre>

<pre> 
testing: 
&nbsp;&nbsp;&nbsp;CustomBigramSet - Can be used for creating combination lists.
&nbsp;&nbsp;&nbsp;NewWordsTrie - Writes matches to the array, can write matches to CSV when testing.
&nbsp;&nbsp;&nbsp;trieDictionary - Creates dictionary and can print statistics on the data structure. </pre>


Resources - Uses dictionary2, ignores dictionary1.




## License
None.

## Acknowledgments
Dr.Greg Durrett (Created original outline for building a language model in bigramLanguageModel package) 

Source: https://www.youtube.com/watch?v=7bf5EdQONTM

## Contact
tatesmouser@gmail.com
