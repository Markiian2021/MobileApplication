package com.example.navigationcomponentsenkiv.data

import com.example.navigationcomponentsenkiv.R

object ItemsData {
    val itemsList = listOf(
        Item(1, R.drawable.lviv1, "Львівська Національна Опера", "Будівля львівського національного академічного театру оперу та театр ім. Крушельницької прикрашає головний проспект міста та стала його справжньою перлиною, об'єктом гордості. Цей театр опери та балету має багату історію, навколо нього плетуться численні легенди. Крім вистав, тут часто розгортаються різноманітні фестивалі та події, що робить його ще більш важливим центром культурного життя. Якщо ви зупинились у Львові, скористайтесь нагодою, відвідайте оперний театр, щоб отримати незабутні спогади та пам'ятні фото на фоні цієї визначної історичної будівлі."),
        Item(2, R.drawable.lviv2, "Церква Пресвятої Євхаристії", "Церква Пресвятої Євхаристії (колишні костел Божого тіла і монастир домініканців) — греко-католицька церква у центральній частині Львова, в зоні всесвітньої спадщини ЮНЕСКО; пам'ятка архітектури національного значення."),
        Item(3, R.drawable.lviv3, "Площа ринок", "Площа Ринок — не лише серце, а й історична душа, де є, що подивитись у Львові, щоб зрозуміти унікальність цього міста. Центральний майдан міста манить туристів своєю вродою та спадщиною. Тут сплітаються минуле і сучасність: старовинні кам'яниці, величні палаци та чотири вишукані фонтани. Аромат кави та смак страв ресторанів і кав'ярень додають неповторності цьому місцю. Тому ця локація ідеально підходить для тих, хто хоче познайомитись з містом та шукає місце, де поїсти у Львові в незвичному, атмосферному закладі."),
        Item(4, R.drawable.lviv4, "Стрийський парк", "Стрийський парк поділяється на три частини: верхню терасу, нижні партери та лісопарк. Арнольд Рерінг розробив проект парку у 1876-77 рр. В 1884 р. тут пройшла Крайова виставка, а до 1922 р. на території парку відбувалися «Східні торги», доступні на трамваї зі станції «Персенківка». В 1951 р. трамвайні шляхи були зняті для дитячої залізниці, але в 1952 р. з'явилася арка біля входу в парк, створена за проектом Г. Швецького-Винецького. Сьогодні парк славиться близько 200 видами дерев і рослин, оранжереєю, алеями платанів і лип, а також озером з лебедями."),
        Item(5, R.drawable.lviv5, "Парк ім. Франка", "Парк імені Івана Франка — стародавній міський оазис в Україні, відомий своїм минулим. Він нагадує про себе через вікові архітектурні перлини, такі як: альтанка-ротонда, свідок грандіозних оркестрових виступів та урочистих подій у XIX ст., а також колишній ресторанний будинок. В 1960-х рр. з'явився пам'ятник патрону університету перед головною будівлею Львівського університету, що став символом цього місця. Для тих, хто шукає місце для сімейного відпочинку або просто варіант, куди піти у Львові на прогуляну, парк ім. Франка – оптимальний варіант. Тут облаштований дитячий майданчик, багато гарних клумб, чимало екзотичних видів рослин та вікових дерев, що стали свідками багатьох історичних подій."),
        Item(6, R.drawable.lviv6, "Високий замок", "Високий Замок відомий також як Замкова гора — місце, звідки відкривається найкращий краєвид на старовинне місто та його околиці, ідеальний варіант, де погуляти у Львові, відпочити та провести незабутнє побачення. Незважаючи на назву, від оригінального замку залишилися лише руїни. Тут розташована висока телевізійна вежа і облаштовано гарний парк. Цей район сполучає історію з сучасністю, пропонуючи видовищні краєвиди і відпочинок серед природи."),
        Item(7, R.drawable.lviv7, "Собор Святого Юра", "Собор Святого Юра — головний храм греко-католицької спільноти у Львові та Львівській архиєпархії. Він служив резиденцією митрополитів Української Греко-Католицької Церкви протягом століть. Собор оточений Митрополичими садами, ідеальними для спокійних прогулянок та відпочинку у Львові."),
    )
}

class Item(val id: Int, val photo: Int, val title: String, val description: String)