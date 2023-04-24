//Caleb Millard
//613362
//Title: ADT data structure Lab
//Lab 3 CMPT 360
//Dr. Rick Sutcliffe




//This ADT was fairly simple to create since javascript already has similar actions within its array manipulation.
//the key different is that we have to make the data type immutable to outside influence other than by the methods we provide
//so you need to be able to see what is inside but not be able to manipulate what is within the stack, and since this is its own ADT
//you need to use the methods to access
class Stack {
    constructor() {
        this.stack = [];
    }
    push(item) {
        //pushes input item into the stack
        this.stack.push(item);
    }
    pop() {
        //removes and returns the last entered item in the stack
        number = this.stack.pop();
        return number
    }
    peek() {
        //returns the last item on the stack without removing it
        peeknum = this[this.length()];
        return peeknum
    }
}
const newstack = new Stack();
//creating a stack data type to use


var i = 0;
while (i < 5) {
    var number = Math.floor(Math.random() * 100 + 1);
    newstack.push(number);
    i++;
    //this inputs 5 inputs into the stack randomly from between 1-100
}
console.log(newstack);
i = 0;
var total = 0;
number = 0;


for (var i = 0; i < 5; i++) {
    //calculates the average of the 5 numbers from the stack
    number = newstack.pop();
    total += number;
}
total = total / 5;
console.log("your Average Grade is " + total);
