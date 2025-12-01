package iv.vas.core_media.audio

import android.media.MediaPlayer
import kotlin.apply

class AudioPlayer {

    private var mediaPlayer : MediaPlayer? = null

    fun play(url : String){
        stop()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(url)

            setOnPreparedListener {
                it.start()
            }

            setOnErrorListener { _, what, extra ->
                println("Error: $what, $extra")
                stop()
                true
            }

            prepareAsync()
        }

    }

    fun stop(){
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }

    fun isPlaying(): Boolean{
        return mediaPlayer?.isPlaying == true
    }
}