#========================================================
# Author:  James Ortiz
# File:    wordAnalyzer.py
# Description: This program is designed to read in a file,
# create a list of lists, and then provide analytical
# information about the text for the user.
# Compile: python3 wordAnalyzer.py
#=========================================================

#Used for file authentication:
import os.path
import sys

# function lexicographicalSort(str)
# Python function to sort the words in lexicographical 
# order
# input: a valid string 'str' type
# returns: a lexicographically sorted list

def lexicographicalSort(myList): 
    # Split the List where space is found. 
    words = myList.split() 
    # sort() will sort the strings. 
    words.sort() 
    #return words of the string as a list:
    return words


# function wordCounter(str, list)
# Python function to count the instance
# of a single word in a list.
# Returns: an integer value

def wordCounter(strng, mylist):
    counter = 0
    for element in mylist:
        if strng == element:
            counter += 1

    return counter



# function map_book(list)
# Python function to create 
# hash-map from a list.
# Returns: a hash-map (dictionary)
def map_book(mylist):
    #Declare empty hash-map:
    hash_map = {}
    
    if mylist is not None:
        for element in mylist:
            #Remove Punctuation:
            word = element.replace(",", "")
            word = word.replace(".", "")

            #Does the word exist?
            if word in hash_map:
                hash_map[word] = hash_map[word] + 1
            else:
                hash_map[word] = 1
    #Return hash_map, else Nothing
        return hash_map
    else:
        return None


def dispInNormalOrder(text):
     print("Text in normal order (preserving case):")
     #Create a single string from the list
     nString = ""
     nString = text
     nString = nString.split()
     s = " "
     s = s.join(nString)
     s = s.replace(' ', '\n')
     return s

def dispInReverseOrder(text):
    print("Text in reverse: (preserving case)")
    nString = ""
    nString = text
    nString = nString.split()
    nString.reverse() #set words in reverse order
    s = " "
    s = s.join(nString)
    s = s. replace(' ', '\n')
    return s

def dispInLexOrder(text):
    print("Text in lexicographical order:")
    nString = ""
    nString = text.lower()
    nString = lexicographicalSort(nString)
    s = " "
    s = s.join(nString)
    s = s.replace(' ', '\n')
    return s

def dispAllCons(text):
     print("Number of consonants in the text")
     consonants  = 0
     chartrs = 0
     nString = ""
     nString = text.lower()
     nString = nString.split()
     s = ""
     s = s.join(nString)
     for elem in s:
         if elem != 'a' and elem != 'e' and elem != 'i' and elem != 'o' and elem != 'u':
             consonants = consonants + 1
             chartrs = chartrs + 1
         else:
             chartrs = chartrs + 1
        
     print("There is a total of: " + str(consonants) + " consonant(s).")
     print("Out of " + str(chartrs) + " characters.")
     return


def dispAllVowels(text):
    print("Number of vowels in the text")
    vowels = 0
    chartrs = 0
    nString = ""
    nString = text.lower()
    nString = nString.split()
    s = ""
    s = s.join(nString)
    for elem in s:
        if elem == 'a' or elem == 'e' or elem == 'i' or elem == 'o' or elem == 'u':
            vowels = vowels + 1
            chartrs = chartrs + 1
        else:
            chartrs = chartrs + 1
    print("There is a total of: " + str(vowels) + " vowel(s).")
    print("Out of " + str(chartrs) + " characters.")
    return

def freqOfWordsNormal(text):
      print("Frequency of words mapped in normal order")
      nString = ""
      nString = text.lower()
      nString = nString.split()
      HashMap = map_book(nString)
      return HashMap

def freqOfWordsReverse(text):
    print("Frequency of words mapped in reverse order")
    nString = ""
    nString = text.lower()
    nString.reverse()
    nString = nString.split()
    HashMap = map_book(nString)
    return HashMap

def freqOfWordsLex(text):
    print("Frequency of words mapped in lexicographical order")
    nString = ""
    nString = text.lower()
    nString = lexicographicalSort(nString)
    HashMap = map_book(nString)
    return HashMap


def freqOfWord(text):
     print("Frequency of the word in the text")
     wordValue = input("Please enter a word: ")
     wordValue = wordValue.lower()
     nString = ""
     nString = text.lower()
     nString = lexicographicalSort(nString)
     numWords = wordCounter(wordValue, nString)
     print("The number of words identical to " + str(wordValue) + " is " + str(numWords))
     return


def main():
    
    print("Welcome to the Word Analyzer!")
    fileNameInput = input("Please enter a file you want to analyze: ")
    if os.path.exists(fileNameInput):
        print("Excellent, reading file: " + fileNameInput)
        file = open(fileNameInput, 'r')
        text = file.read().strip()
        file.close()
    else:
        print("Cannot find file: " + fileNameInput)
        print("Closing program...")
        sys.exit() #Early Exit
        
        
        
    doContinue = True
        




    while True:
        print("-----------------------------------------------------------")
        print("========================Main Menu==========================")
        print("-----------------------------------------------------------")
        print("Please select an option: ----------------------------------")
        print("Display words in normal order --------------------------(n)")
        print("Display words in reverse order -------------------------(r)")
        print("Display words in lexicographical order -----------------(l)")
        print("Analyze number of consonants in file ------------------(nc)")
        print("Analyze number of vowels in file ----------------------(nv)")
        print("Show frequency of words mapped in normal order --------(nf)")
        print("Show frequency of words mapped in reverse order -------(rf)")
        print("Show frequency of words mapped in lex. order ----------(lf)")
        print("Show frequency of one word in file --------------------(ow)")
        print("Exit program ------------------------------------------(ep)")
    
        #Gain input from user:
        selectOption = input("Please select a value: ")
        
        if selectOption  == "n" or selectOption == "N":
            #Display words in normal order (preserving case)
            s = dispInNormalOrder(text)
            print(s)
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            if proceed == "y":
                continue
            else:
                sys.exit()
                
        elif selectOption == "r" or selectOption == "R":
            #Display words in reverse order,
            #preserving case:
            s = dispInReverseOrder(text)
            print(s)
            
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            
            if proceed == "y":
                continue
            else:
                sys.exit()
        
        elif selectOption == "l" or selectOption == "L":
            #Display result lexicographically:
            s = dispInLexOrder(text)
            print(s)
            
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            
            if proceed == "y":
                continue
            else:
                sys.exit()
                
        elif selectOption == "nc" or selectOption == "NC":
            #Display all consonants in text:
            dispAllCons(text)
            
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            
            if proceed == "y":
                continue
            else:
                sys.exit()
                
        elif selectOption == "nv" or selectOption == "NV":
            #Display all vowels in text:
            dispAllVowels(text)
            
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            
            if proceed == "y":
                continue
            else:
                sys.exit()
                
                
        elif selectOption == "nf" or selectOption == "NF":
            #Display the frequency of words mapped in normal order
            NMap = freqOfWordsNormal(text)
            for key, value in NMap.items():
                pValues = '{:>12}  {:>12}'.format(str(key), str(value))
                print(pValues)
                
                
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
                
            if proceed == "y":
                continue
            else:
                sys.exit()
                    
        elif selectOption == "rf" or selectOption == "RF":
        #Display the frequency of words mapped in reverse order
            NMap = freqOfWordsReverse(text)
            for key, value in NMap.items():
                pValues = '{:>12}  {:>12}'.format(str(key), str(value))
                print(pValues)
                
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()

            if proceed == "y":
                doContinue = True
            else:
                sys.exit()
            
        elif selectOption == "lf" or selectOption == "LF":
            #Display frequency of words in lex. order
            NMap = freqOfWordsLex(text)
            for key, value in NMap.items():
                pValues = '{:>12}  {:>12}'.format(str(key), str(value))
                print(pValues)

            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()

            if proceed == "y":
                doContinue = True
            else:
                sys.exit()
    
        elif selectOption == "ow" or selectOption == "OW":
            #Find the frequency of one word in the text:
            freqOfWord(text)
            
            proceed = input("Do you wish to continue? (Select Y/N): ")
            proceed.lower()
            
            if proceed == "y":
                continue
            else:
                sys.exit()
            
        elif selectOption == "ep" or selectOption == "EP":
            #Quit program
            print("Quitting Program...")
            print("Thanks for using Word Analyzer!")
            doContinue = False
            sys.exit()
        else:
            #Otherwise, erroneous input:
            print("Erroneous Input, try again")
            doContinue = False
    if(doContinue == False):
        sys.exit()

if __name__ == "__main__":
    main()