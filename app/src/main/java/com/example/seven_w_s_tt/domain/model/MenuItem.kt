package com.example.seven_w_s_tt.domain.model

data class MenuItem(
     val id: Int,
     val name: String,
     val imageURL: String,
     val price: Int
)

val mockMenuItems = listOf(
     MenuItem(
          id = 1,
          name = "Кофе 1",
          imageURL = "https://tea.ru/upload/blog/0821/3108/coffee/01.jpg",
          price = 100
     ),
     MenuItem(
          id = 2,
          name = "Кофе 2",
          imageURL = "https://www.cre.ru/content/upload/news/15528982809641.jpeg",
          price = 150
     ),
     MenuItem(
          id = 3,
          name = "Кофе 3",
          imageURL = "https://ichef.bbci.co.uk/news/640/cpsprodpb/B079/production/_117677154_gettyimages-156586025.jpg",
          price = 240
     )
)
