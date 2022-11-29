package com.samplecode.videotest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.common.net.MediaType
import com.samplecode.videotest.databinding.ActivityMainBinding
import com.samplecode.videotest.databinding.MotionTestBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MotionTestBinding

    private val exoPlayers = mutableListOf<ExoPlayer>()
    private val playersMedia = mutableListOf<MediaItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = MotionTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populatePlayers(binding.player1,
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        populatePlayers(binding.player2,
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        populatePlayers(binding.player3,
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
        populatePlayers(binding.player4,
            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4")
    }

    private fun populatePlayers(ui: StyledPlayerView, uri: String) {
        val media = MediaItem.fromUri(uri)

        val player = ExoPlayer.Builder(this).build().apply {
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            volume = 0F
        }
        ui.player = player

        player.setMediaItem(media)
        player.prepare()
        player.play()

        exoPlayers.add(player)
        playersMedia.add(media)
    }

    override fun onDestroy() {
        super.onDestroy()
        for (exoPlayer in exoPlayers) {
            exoPlayer.stop()
            exoPlayer.release()
        }
    }
}