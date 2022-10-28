package com.example.poemrecycleview

class PoemLibrary {
    companion object {
        fun getPoemList(): List<Poem> {
            return listOf(
                Poem(
                    "望庐山瀑布", "李白",
                    listOf(
                        "日照香炉生紫烟，",
                        "遥看瀑布挂前川。",
                        "飞流直下三千尺，",
                        "疑是银河落九天。"
                    )
                ), Poem(
                    "早发白帝城", "李白",
                    listOf(
                        "朝辞白帝彩云间，",
                        "千里江陵一日还。",
                        "两岸猿声啼不住，",
                        "轻舟已过万重山。"
                    )
                ), Poem(
                    "如梦令·昨夜雨疏风骤", "李清照",
                    listOf(
                        "昨夜雨疏风骤，",
                        "浓睡不消残酒。",
                        "试问卷帘人，",
                        "却道海棠依旧。",
                        "知否，知否，",
                        "应是绿肥红瘦。"
                    )
                ), Poem(
                    "春望", "杜甫",
                    listOf(
                        "国破山河在，",
                        "城春草木深。",
                        "感时花溅泪，",
                        "恨别鸟惊心。",
                        "烽火连三月，",
                        "家书抵万金。",
                        "白头搔更短，",
                        "浑欲不胜簪。"
                    )
                )
            )
        }
    }
}