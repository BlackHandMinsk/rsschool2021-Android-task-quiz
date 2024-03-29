package com.rsschool.quiz

fun getQuestionsForQuiz(): ArrayList<Question> {
    val questionOne = Question("Основой для «Сказки о рыбаке и рыбке Пушкина послужила сказка братьев Гримм «Рыбак и его жена». В ней немецкая «коллега» нашей старухи превратилась в:",arrayListOf("Папу Римского","Королеву","Директора рыбзавода","Командира отряда водолазов ","Русалку"),"Папу Римского")
    val questionTwo = Question("Российский мультфильм, удостоенный «Оскара», — это…",arrayListOf("Простоквашино","Винни-Пух","Старик и море","Ну, погоди!","Маша и медведи"),"Старик и море")
    val questionThree = Question("Что в Российской империи было вещевым эквивалентом денег?",arrayListOf("Шкуры пушных зверей","Крупный рогатый скот","Табак","Женские серьги","Водка"),"Шкуры пушных зверей")
    val questionFour = Question("У индейцев из немногочисленного североамериканского племени квакиутл есть традиция: беря деньги в долг, они оставляют в залог…",arrayListOf("Душу","Имя","Скальп тещи","Амулет","Первого ребенка"),"Имя")
    val questionFive = Question("Высота Останкинской башни — 540 метров. Сколько нужно размотать рулонов туалетной бумаги, чтобы получилось столько же метров? ",arrayListOf("1","5","3","10","25"),"10")
    val questions = arrayListOf<Question>(questionOne,questionTwo,questionThree,questionFour,questionFive)
    return questions
}