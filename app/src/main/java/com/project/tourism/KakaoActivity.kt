package com.project.tourism

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.project.tourism.ui.theme.TourismTheme

class KakaoActivity : ComponentActivity() {

    private val kakaoAuthViewModel : KakaoAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TourismTheme {
                Surface(
                   modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //KakaoLoginTool()
                    KakaoLoginView(kakaoAuthViewModel)
                }
            }
            //var keyHash = Utility.getKeyHash(this)
            //Log.e("hash","keyHash:$keyHash")
        }
    }
}

@Composable
fun KakaoLoginView(viewModel: KakaoAuthViewModel){

    val isLoggedIn = viewModel.isLoggedIn.collectAsState()

    val loginStatusInfoTitle = if(isLoggedIn.value) "Logged In" else "Logged Out"

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.kakaoLogin()
        }) {
            Text(text = "카카오 로그인")
        }
        Button(onClick = {
            viewModel.kakaoLogout()
        }) {
            Text(text = "카카오 로그아웃하기")
        }

        Text(loginStatusInfoTitle, textAlign = TextAlign.Center, fontSize = 20.sp)
    }
}

/*
@Composable
fun KakaoLoginTool() {
    var _context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        Button(onClick = {
            kakaoLogin_step(_context)
        }) {
            Text("Kakao 로그인")
        }
    }
}

fun kakaoLogin_step(_context: Context) {
    //카카오톡 로그인 실행
    UserApiClient.instance.loginWithKakaoTalk(_context) { token, error ->
        if(error != null){
            Log.e("code1","로그인 실패",error)
        }
        else if(token != null){
            Log.e("code2","로그인 성공 ${token.accessToken}")
        }
    }
}*/
