// --- JAVASCRIPT LEARNING: STEP 1 (Variables & Basic Data Types) ---








/*
   EXPLANATION:
   In JavaScript, we use 'let' and 'const' to store information (data).
   - 'let' is used when the value might change later.
   - 'const' is used when the value should stay the same (constant).
   
   Data Types:
   - String: Text wrapped in quotes, like "Hello".
   - Number: Plain numbers, like 25 or 10.5.
*/

// QUESTION 1: 
// Create a variable using 'let' named 'myName' and store your name in it as a String.
let myName = "Dhanush"
console.log(myName)
// Then, on the next line, change the value of 'myName' to a different name.
myName = "Dhanush Kumar"
console.log(myName)

// QUESTION 2:
// Create a variable using 'const' named 'birthYear' and store your birth year as a Number.
const birthYear = 2003

// QUESTION 3:
// Create a variable named 'isLearning' and store the value 'true' (this is a Boolean).
let islearning = true

// QUESTION 4:
// Use 'console.log()' to print out 'myName', 'birthYear', and 'isLearning' to the console.
console.log(myName, birthYear, islearning);

// --- JAVASCRIPT LEARNING: STEP 2 (Basic Math & Template Literals) ---

/* 
   EXPLANATION:
   JavaScript can do math like a calculator:
   + (add), - (subtract), * (multiply), / (divide).

   Template Literals:
   Instead of using quotes like " " or ' ', we use backticks ` ` (the key below Esc).
   This allows us to inject variables directly into a sentence using ${variableName}.
   Example: `My name is ${myName}`
*/

// QUESTION 5:
// Create a variable named 'currentYear' and set it to the current year.
// Then create a variable named 'age' and calculate it by subtracting 'birthYear' from 'currentYear'.
let currentYear = 2026
let age = currentYear - birthYear
console.log(age)

// QUESTION 6:
// Use a Template Literal (backticks) to create a sentence: 
// "Hello, my name is [myName] and I am [age] years old."
// Store this sentence in a variable named 'greeting' and then console.log(greeting).
let greeting = `Hello, my name is ${myName} and i am ${age} years old.`
console.log(greeting);

// QUESTION 7:
// Create two number variables 'num1' and 'num2'. 
// Multiply them and store the result in 'product'.
// Print the result.
let num1 = 12
let num2 = 2
let product = num1 * num2
console.log(product);

// --- JAVASCRIPT LEARNING: STEP 3 (Conditionals - Making Decisions) ---

/* 
   EXPLANATION:
   Conditionals use 'if' and 'else' to run different code depending on a condition.
   We use comparison symbols:
   >  (greater than)
   <  (less than)
   === (is exactly equal to)
   >= (greater than or equal to)
   !== (is NOT equal to)
*/

// QUESTION 8:
// Write an 'if/else' statement.
// If 'age' is 18 or older, console.log("You are an adult.").
if (age >= 18)
    console.log("you are an adult.")
else
    console.log("you are a minor.")
// Otherwise, console.log("You are a minor.").


// QUESTION 9:
// Create a variable 'isHungry' and set it to 'true'.
// Write an 'if' statement that checks if 'isHungry' is true.
// If it is, console.log("Time to eat!").
let isHungry = true
if (isHungry)
    console.log("Time to eat!")

// QUESTION 10:
// Create a variable 'favoriteColor' and set it to your favorite color.
// Write an 'if/else' statement:
// If 'favoriteColor' is "Blue", log "That's a cool color!".
// Otherwise, log "I like that color too!".
let favoriteColor = "Blue"
if (favoriteColor === "Blue")
    console.log("That's a cool color!")
else
    console.log("I like that color too!")

// --- JAVASCRIPT LEARNING: STEP 3.5 (Tricky Topics & Exceptions) ---

/* 
   EXPLANATION:
   1. Loose (==) vs Strict (===): 
      - Loose equality (==) tries to convert types to match (e.g., 5 == "5" is true).
      - Strict equality (===) checks both value and type (e.g., 5 === "5" is false).
   
   2. Truthy and Falsy:
      - Certain values are automatically 'false' when used in an 'if' check:
        0, "" (empty string), null, undefined, and NaN.
   
   3. Math with Strings:
      - '+' is used for both adding numbers AND joining text (concatenation).
      - '-', '*', and '/' only work with numbers, so JS tries to convert text to numbers.
*/

// QUESTION 11:
// Create a variable 'val1' = 10 (number) and 'val2' = "10" (string).
// Use '==' to compare them and log "Loose Match".
// Use '===' to compare them and log "Strict Match".
let val1 = 10
let val2 = "10"
if (val1 == val2)
    console.log("Loose Match")
else
    console.log("Strict Match")

// QUESTION 12:
// Create a variable 'username' and set it to an empty string "".
// Write an 'if/else' statement checking 'username'. 
// If true, log "Welcome!". If false, log "Please enter a name.".
// (This tests 'Falsy' values).
let username = ""
if (username)
    console.log("Welcome!")
else
    console.log("Please enter a name.")

// QUESTION 13:
// Create a variable 'result1' = 20 + "5"
// Create a variable 'result2' = 20 - "5"
// Log both. One will be 205, the other will be 15. Can you guess which?
let result1 = 20 + "5"
let result2 = 20 - "5"
console.log(result1)
console.log(result2)

// --- JAVASCRIPT LEARNING: STEP 4 (Arrays - Lists of Data) ---

/* 
   EXPLANATION:
   An Array is a list of items inside square brackets [ ].
   - Items are separated by commas.
   - We find items using their "Index" (position).
   - IMPORTANT: Counting starts at 0!
     Example: const colors = ["Red", "Green", "Blue"];
     colors[0] is "Red", colors[1] is "Green".
*/

// QUESTION 14:
// Create an array named 'hobbies' with at least 3 things you like to do.
// Use console.log to print ONLY the first hobby in the list.
let hobbies = ["reading", "coding", "gaming"]
console.log(hobbies[0])

// QUESTION 15:
// Arrays have "methods" (built-in tools). 
// Use 'hobbies.push("Coding")' to add a new item to the end.
// Then use console.log(hobbies) to see the updated list.
hobbies.push("Coding")
console.log(hobbies)


// QUESTION 16:
// How many items are in your list?
// Use 'hobbies.length' to find out and log the result.
console.log(hobbies.length)

// QUESTION 17 (Tricky):
// What happens if you try to log an index that doesn't exist?
// Try to log 'hobbies[10]' and see what the computer says.
console.log(hobbies[10])


// --- JAVASCRIPT LEARNING: STEP 5 (Loops - Repeating Tasks) ---

/* 
   EXPLANATION:
   A 'for' loop lets you run code multiple times. It has 3 parts:
   1. Initialization: let i = 0 (Start counting at 0)
   2. Condition: i < 5 (Keep going as long as this is true)
   3. Increment: i++ (Add 1 to 'i' after every loop)

   Example:
   for (let i = 0; i < 5; i++) {
       console.log("This is loop number: " + i);
   }
*/

// QUESTION 18:
// Write a 'for' loop that logs the numbers from 1 to 5.
for (let i = 1; i < 6; i++) {
    console.log(i)
}

// QUESTION 19:
// Now, let's use a loop to "walk" through your array.
// Write a 'for' loop that logs every hobby in your 'hobbies' array.
// Hint: Use 'i < hobbies.length' so it stops at the right time.
for (let i = 0; i < hobbies.length; i++) {
    console.log(hobbies[i])
}

// QUESTION 20 (Tricky):
// Summing numbers!
// Create a variable 'total = 0'.
// Use a loop to add every number from 1 to 10 to 'total'.
// At the end, log the 'total'. (The answer should be 55).

let total = 0
for (let i = 1; i < 11; i++) {
    total += i
}
console.log(total)


// QUESTION 21 (Exceptional):
// Can we skip numbers?
// Write a loop that counts from 0 to 10, but only logs the EVEN numbers (0, 2, 4, 6, 8, 10).
// Hint: Instead of 'i++', use 'i = i + 2'.
for (let i = 0; i < 11; i += 2) {
    console.log(i)
}
// --- JAVASCRIPT LEARNING: STEP 6 (Functions - Reusable Recipes) ---

/*
   EXPLANATION:
   A Function is a block of code you can reuse.
   Think of it as a "Recipe" that you can cook whenever you need it.

   1. Declaration: function name(parameter) { ... }
   2. Calling: name("actual data");

   Parameters are like "empty slots" that get filled when you run the function.
*/

// QUESTION 22:
// Write a function named 'greet' that takes a 'name' as a parameter.
// It should log: "Hello [name], have a great day!".
// Call this function 3 times with 3 different names.
function greet(name) {
    console.log(`hello ${name}, have a great day!`)
}
greet("rohit")
greet("dhanu")
greet("kush")



// QUESTION 23 (The 'return' Keyword):
// Sometimes we don't want to log; we want a result we can use later.
// Write a function named 'addNumbers' that takes 'numA' and 'numB'.
// Inside, use 'return numA + numB;'.
// Store the result of calling addNumbers(10, 20) in a variable named 'sum' and log it.

function addNumbers(numA, numB){
    return numA + numB
}
let sum= addNumbers(10,20)
console.log(sum)
// QUESTION 24 (Tricky - Scope):
// Variables created INSIDE a function are like "secrets" only that function knows.
// Write a function called 'mySecret'. Inside it, create 'let secret = "I am hidden"'.
// Try to console.log(secret) OUTSIDE the function (on the very last line).
// What error do you get?

function mySecret(){
    let secret = "I am hidden"
}
console.log(secret)

// --- JAVASCRIPT LEARNING: STEP 7 (Objects - Key-Value Pairs) ---

/* 
   EXPLANATION:
   An Object is like a "dictionary" or a "profile". 
   Instead of just a list of items, every piece of data has a NAME.

   Example:
   const phone = {
       brand: "Apple",
       model: "iPhone 15",
       price: 999
   };

   - We access data using a dot: phone.brand
   - We can also use brackets: phone["brand"]
*/

// QUESTION 25:
// Create an object named 'userProfile'.
// Give it these keys: 'name', 'city', and 'isOnline' (a boolean).
// Use console.log to print ONLY the city.


// QUESTION 26:
// You can update objects just like variables!
// Change the 'city' in your 'userProfile' to a different city name.
// Then add a completely NEW key called 'favoriteFood' and set it to something.
// Log the whole object to see the changes.


// QUESTION 27 (Methods):
// Objects can hold functions! This is how we make objects "do" things.
// Add a function called 'sayHi' inside your object.
// It should log: "Hello from [name]!".
// Hint: Inside an object, use 'this.name' to talk about yourself.
// Then call it using: userProfile.sayHi();


// QUESTION 28 (Exceptional - Reference):
// This is the most famous "Tricky" part of Objects.
// Create an object 'a = { score: 10 }'.
// Create another variable 'b = a'. (This doesn't copy it; it points to it!)
// Change 'b.score = 20'.
// Log 'a.score'. Did it change too? Why?












