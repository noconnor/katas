

## Objective 

Source: https://www.codewars.com/kata/binary-search/javascript
        http://en.wikipedia.org/wiki/Binary_search_algorithm

Binary search: logarithmic time [O(log n)](https://en.wikipedia.org/wiki/Time_complexity#Logarithmic_time)
              

A binary search algorithm is a way to look for the index of a certain value in a sorted array.
The algorithm will progressively cut the original array into smaller and smaller chunks until it finds the desired value. 
If the desired value is not found, return -1.
The array *MUST* be sorted.

Let's take a look at an illustration from Wikipedia:

The list to be searched: L = 1 3 4 6 8 9 11. The value to be found: X = 4.
```
Compare X to 6. X is smaller. Repeat with L = 1 3 4.
Compare X to 3. X is bigger. Repeat with L = 4.
Compare X to 4. They are equal. We're done, we found X.
```
<br />

## Algorithms

http://en.wikipedia.org/wiki/Binary_search_algorithm

<br />



