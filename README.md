# WunderNut Vol9 Solution

My take on the current [WunderNut](https://github.com/wunderdogsw/wunderpahkina-vol9) puzzle. 

I'm sure this is not the most elegant solution, nor the one with best performance, but it works and showcases my coding style. 

Oh, but actually it does not work.  

Reading the task again there is 
"Note that pattern width isn’t limited to the width given in the input file; you can assume an infinite number of empty squares exist preceding and succeeding the pattern."

So if I understand right then is should go like this

Given:

. # . # # #

Should make the next line one wider

. . # . # # #

This I don't have, I have static width.

Oh well.. There might be other errors as well, but I'm late for the competition anyway so this is good enough. 
