package com.rsschool.quiz

fun getQuestionsForQuiz(): ArrayList<Question> {
    val questionOne = Question("1+1",arrayListOf("1","2","3","4","5"),"2")
    val questionTwo = Question("1+2",arrayListOf("1","2","3","4","5"),"3")
    val questionThree = Question("1+3",arrayListOf("1","2","3","4","5"),"4")
    val questionFour = Question("1+4",arrayListOf("1","2","3","4","5"),"5")
    val questionFive = Question("1+5",arrayListOf("1","6","3","4","5"),"6")
    val questions = arrayListOf<Question>(questionOne,questionTwo,questionThree,questionFour,questionFive)
    return questions
}