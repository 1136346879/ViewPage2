package com.example.viewpager2demo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Created by Splash on 2020/6/19.
 * Author：wdx
 * Description： This is 。。。
 **/
class Splash : AppCompatActivity() {
    var videoView: VideoView? = null
    var imageView: ImageView? = null
    var videoPath: String? = null
    var btn_jump: Button? = null
    var playProgress = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        videoView = findViewById<View>(R.id.video_view) as VideoView?
        imageView = findViewById<View>(R.id.image_view) as ImageView?
        btn_jump = findViewById<View>(R.id.btn_jump) as Button?
        //申请权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 0)
        } else {
            playVideo()
        }
        btn_jump!!.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }

    open fun playVideo() {
        videoPath = Environment.getExternalStorageDirectory().absolutePath + "/" + "intro.mp4"
        val media = MediaMetadataRetriever()
        val url = "https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/common/20200627095227017284.mp4"
//        val url = "http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8"
        val uri: Uri = Uri.parse(videoPath)
        media.setDataSource(videoPath)
//        media.setDataSource(url)
        //获取第一帧
        val bitmap = media.getFrameAtTime(1, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        imageView!!.setImageBitmap(bitmap)
        media.release()
        videoView!!.setVideoPath(url)
//        videoView!!.setVideoURI(uri)
//        videoView!!.setVideoPath(videoPath)
//        videoView!!.requestFocus()
        videoView!!.start()
        videoView!!.setOnPreparedListener { mediaPlayer ->
            // imageView.setVisibility(View.GONE);
            mediaPlayer.isLooping = true
            mediaPlayer.setOnInfoListener(MediaPlayer.OnInfoListener { mediaPlayer, what, i1 -> //开始播放时，就把显示第一帧的ImageView gone 掉
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    // video started; hide the placeholder.
                    imageView!!.visibility = View.GONE
                    //videoView.seekTo(0);
                    return@OnInfoListener true
                }
                false
            })
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            playVideo()
        }
    }

    override fun onPause() {
        super.onPause()
        //记录播放的progress,避免黑屏
        videoView!!.pause()
        playProgress = videoView!!.currentPosition
    }

    override fun onResume() {
        super.onResume()
        if (playProgress != 0) {
            videoView!!.seekTo(playProgress)
            videoView!!.start()
        }
    }
}