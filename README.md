# genetic-one-tough-puzzle-solver
## About
This is project uses a genetic evolution algorithm to find a solution to the brainteaser <a href="https://www.amazon.com/s/?ie=UTF8&keywords=one+tough+puzzle">One Tough Puzzle</a>.
It is a follow-up attempt to a program I wrote in my first year at university as a computer science major. My initial solution used several nested for loops to iterate over every possible position and rotatuion of each of the peices. Needless to say, it didn't work within any reasonable amount of time.
## Method
This solution includes a generic Evolution package which I wrote for this project (I can't stand to not make something generic). The main Ecosystem class is a generic template to find optimal versions of its implemented IOrganism objects through the progression of multiple generations.
<br />
### Genetic Functions
The One Tough Puzzle implementation of the Evolution package performs <b>cross-over</b> by taking either rows or columns from one parent and combining them with rows or columns from the other.
<b>Mutation</b> is performed by swapping or rotating a single puzzle-piece of a proposed solution.
<b>Fitness</b> is measured by the number of fitting connections between puzzle pieces (12 connections meaning a solved puzzle).
