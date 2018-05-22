

## Objective 

Source: https://stackoverflow.com/questions/27318621/how-to-start-the-bucket-fill-algorithm

Pretend you are working in MS Paint, or a similar graphics application. 
Your job is to implement the bucket-fill tool. 
More specifically, given a two-dimensional canvas, an (X,Y) position 
that the user clicked on, and a color, devise an algorithm that can 
be used to fill appropriate part of the canvas.

<br />

## Rules

The bucket-fill algorithm should:
* 'paint' all pixels that are connected to the pixel that the user clicked on, 
all the way to the borders where the color changes. 
* So for example, if the user clicks on a white pixel, and specifies the color green, 
the bucket-fill tool will turn all of the touching white pixels into green pixels. 
* It will not, however, affect white pixels that are in a completely separate part 
of the image.

## Algorithms

* [Stack-based recursive implementation](src/main/java/com/github/noconnor/reference/RecursiveFill.java)
* [Queue-based implementation](src/main/java/com/github/noconnor/reference/QueueFill.java)

https://en.wikipedia.org/wiki/Flood_fill

<br />



