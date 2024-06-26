package org.d3if0041.mopro1.proyekcoba.halaman

sealed class Screen (val route:String) {
    data object Awal: Screen("awalScreen")
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")
    data object Open: Screen("openScreen")
    data object Home: Screen("mainScreen")
    data object List: Screen("listScreen")
    data object Lain: Screen("lainScreen")
    data object Info: Screen("infoScreen")
    data object Password: Screen("passwordScreen")
    data object Chart: Screen("chartScreen")
    data object Entri: Screen("entriScreen")
    data object Add: Screen("addScreen")
    data object Tips: Screen("tipsScreen")
    data object Jogging: Screen("joggingScreen")
    data object Butterfly: Screen("butterflyScreen")
    data object Deep: Screen("deepScreen")
    data object Imagery: Screen("imageryScreen")
    data object Jalan: Screen("jalanScreen")
    data object Yoga: Screen("yogaScreen")
    data object Muscle: Screen("muscleScreen")
}