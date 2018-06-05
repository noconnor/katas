## Objective 

1. [Implement bubble sort algorithm](src/main/java/com/github/noconnor/reference/BubbleSort.java)
2. [Implement quick sort algorithm](src/main/java/com/github/noconnor/reference/QuickSort.java)
3. [Implement merge sort algorithm](src/main/java/com/github/noconnor/reference/MergeSort.java)
4. [Implement a selection sort](src/main/java/com/github/noconnor/reference/SelectionSort.java)
5. [Implement an insertion sort](src/main/java/com/github/noconnor/reference/InsertionSort.java)

<br>

## Complexity

https://en.wikipedia.org/wiki/Sorting_algorithm#Comparison_of_algorithms


|Algorithm| Time - Worst case| Time - Best case | Time - Average | Space - Worst case|
|:---|:---|:---|:---|:---|
|Bubble Sort|O(n^2)     |O(n)       |O(n^2)     |O(1)| 
|Quick Sort |O(n^2)     |O(n*log(n))|O(n*log(n))|O(log n)|
|Merge Sort |O(n*log(n))|O(n*log(n))|O(n*log(n))|O(n)|
|Selection Sort |O(n^2)|O(n^2)|O(n^2)|O(1)|
|Insertion Sort |O(n^2)|O(n)|O(n^2)|O(n)|


<br>

## Reference

* [Bubble Sort](https://en.wikipedia.org/wiki/Sorting_algorithm#Bubble_sort)
  * [Bubble sort java reference](https://www.mkyong.com/java/java-bubble-sort-example/)
  * **Overview:** Repeatedly iterate over input data set and compare adjacent entries, swap entries if they are in teh wrong order
* [Quick Sort](https://en.wikipedia.org/wiki/Quicksort)
  * [Quick sort java reference](https://www.geeksforgeeks.org/quick-sort/) 
  * **Overview:** Pick a "pivot point", reorder data set to all smaller elements come before the pivot point, larger values after the pivot point. Recursively apply this to sub sets either side of the pivot.
* [Merge Sort](https://en.wikipedia.org/wiki/Merge_sort)
  * [Merge sort java reference](https://www.geeksforgeeks.org/merge-sort/)
  * **Overview:** Stable sort (preserves the input order of equal elements). Divide and conquer. Divide unsorted list into n sub-lists of 1 element, repeatedly merge the sublists, sorting as merging.  
* [Selection Sort](https://en.wikipedia.org/wiki/Selection_sort)
  * [Selection sort java reference](https://www.geeksforgeeks.org/selection-sort/)
  * **Overview:** Divide data set into subsets (sorted and unsorted). Repeatedly find smallest element in unsorted set and move to correct position in sorted section of data set.
* [Insertion Sort](https://en.wikipedia.org/wiki/Insertion_sort)
  * [Insertion sort java reference](https://www.geeksforgeeks.org/insertion-sort/)
  * **Overview:** Like manually sorting a hand of cards. At each iteration, remove one entry from input data, find the location it belongs to within the sorted list and insert it. Repeat. 

<br>

## Pros & cons

|Algorithm| Pros | Cons|
|:--------|:-----|:----|
|Bubble Sort|* Good on extremely small datasets|* O(n^2) time complexity|
|Merge Sort|* Better than quick sort when sorting linked lists<br> * Uses less comparisons than Quick sort|* Worst case memory is O(n)|
|Quick Sort|* Worst case memory complexity is O(log n)<br> * can be 2-3 times faster than merge sort and heap sort|* Not good if dataset doesn't fit into memory|
|Selection Sort|* Simple algorithm<br> * Fast for small arrays (faster than merge sort for 10 - 20 elements)|* O(n^2) complexity, therefore inefficient on large datasets|
|Insertion Sort|* Aimple algorithm<br> * Fast for small arrays (faster than selection sort)|* O(n^2) complexity, therefore inefficient on large datasets|

<br>

## Example


**Quick Sort**:
```
arr[] = {10, 80, 30, 90, 40, 50, 70}
Indexes:  0   1   2   3   4   5   6 

low = 0, high =  6, pivot = arr[h] = 70
Initialize index of smaller element, i = -1

Traverse elements from j = low to high-1
j = 0 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 0 
arr[] = {10, 80, 30, 90, 40, 50, 70} // No change as i and j 
                                     // are same

j = 1 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 2 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 1
arr[] = {10, 30, 80, 90, 40, 50, 70} // We swap 80 and 30 

j = 3 : Since arr[j] > pivot, do nothing
// No change in i and arr[]

j = 4 : Since arr[j] <= pivot, do i++ and swap(arr[i], arr[j])
i = 2
arr[] = {10, 30, 40, 90, 80, 50, 70} // 80 and 40 Swapped
j = 5 : Since arr[j] <= pivot, do i++ and swap arr[i] with arr[j] 
i = 3 
arr[] = {10, 30, 40, 50, 80, 90, 70} // 90 and 50 Swapped 

We come out of loop because j is now equal to high-1.
Finally we place pivot at correct position by swapping
arr[i+1] and arr[high] (or pivot) 
arr[] = {10, 30, 40, 50, 70, 90, 80} // 80 and 70 Swapped 

Now 70 is at its correct place. All elements smaller than
70 are before it and all elements greater than 70 are after
it.

```
