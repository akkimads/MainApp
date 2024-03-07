package com.example.findmyip.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.findmyip.network.IpInfo
import com.example.findmyip.util.ApiState
import com.example.findmyip.viewmodels.UserViewModel


@Composable
fun UserListScreen(viewModel: UserViewModel) {

    when (val result = viewModel.response.value) {
        is ApiState.Success -> {


            Column(Modifier.padding(25.dp)) {
                result.dataSealed.body()?.asn?.let { Text(text = it) }
                result.dataSealed.body()?.ip?.let { Text(text = it) }
                result.dataSealed.body()?.network?.let { Text(text = it) }
                result.dataSealed.body()?.version?.let { Text(text = it) }
                result.dataSealed.body()?.country?.let { Text(text = it) }
            }
            Log.d("success result", result.dataSealed.body().toString())

        }

        is ApiState.Failure -> {
            Text(text = "${result.msg}")
            Log.d("akku", "fail ")

        }

        ApiState.Loading -> {
            CircularProgressIndicator()
            Log.d("akku", "load ")

        }

        ApiState.Empty -> {
            Log.d("akku", "empty ")

        }
    }
}

@Composable
fun EachRow(post: Int) {
    TODO("Not yet implemented")
}
