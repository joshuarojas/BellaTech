package com.samuelchowi.bellatech.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelchowi.bellatech.R
import com.samuelchowi.bellatech.main.model.StepModel
import com.samuelchowi.bellatech.secondary.SecondaryActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            StepModel(
                "Primera pagina!",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis at consectetur massa, sit amet volutpat nunc. Morbi pellentesque nibh leo, pharetra consectetur sem cursus ac. Nunc sit amet odio nulla. Nulla aliquet vel lacus eget rhoncus. Cras viverra, odio ut dignissim finibus, metus purus porta justo, in ullamcorper leo urna vel lacus. Aenean rhoncus bibendum lacus, at laoreet elit efficitur sed. Etiam laoreet ante lacinia mollis cursus."
            ),
            StepModel(
                "Vamos avanzando",
                "Sed non lorem cursus, elementum lacus vel, efficitur ipsum. Nullam ac sem libero. Cras tincidunt, justo ut vestibulum pharetra, eros urna rhoncus sapien, vitae malesuada neque est dapibus lacus. Sed feugiat ex eu nulla lobortis finibus. In tincidunt ex sed ante imperdiet egestas. Curabitur elementum nulla non elit gravida, a ultricies odio condimentum. In vehicula sit amet metus sit amet ultrices. Etiam a nisi nulla. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam dapibus porta dolor. Donec efficitur risus urna, vitae ultrices nisi interdum sit amet."
            ),
            StepModel(
                "Tercera!",
                "In hac habitasse platea dictumst. Etiam pellentesque erat nec magna pellentesque imperdiet. Cras lobortis aliquet ante, non semper ante mattis lacinia. Donec congue tellus et consectetur lacinia. Aliquam et cursus nulla. Duis sit amet molestie ipsum. Mauris venenatis, nisi at commodo ultricies, magna est iaculis lectus, sit amet commodo dui ipsum sit amet arcu."
            ),
            StepModel(
                "Ya caso llegamos",
                "Nunc accumsan sem dui, et viverra felis ultricies vitae. Donec at massa libero. Donec pellentesque vestibulum est at aliquam. Pellentesque in elementum sem, et placerat nisl. Vivamus vulputate nec purus quis lacinia. Ut lacus augue, dapibus molestie magna vel, eleifend porta urna. Praesent et posuere velit. Morbi finibus, diam vitae hendrerit interdum, leo risus dictum dolor, et elementum nisi arcu eget est. Suspendisse potenti. Praesent id porta velit, at gravida lacus. Suspendisse commodo velit in venenatis efficitur. Etiam nec volutpat eros. Pellentesque euismod libero non turpis convallis, nec auctor nunc imperdiet. Pellentesque ac congue diam. Pellentesque sit amet dui metus."
            ),
            StepModel(
                "Un poquitin mas",
                "Phasellus vel risus ut dolor malesuada ultrices non a ipsum. Etiam sit amet sem et risus porttitor mollis et hendrerit nulla. Etiam quis massa id nibh vestibulum malesuada sed in augue. Cras accumsan eros non erat aliquam posuere. Aenean sodales risus ultrices ipsum sagittis, sit amet viverra velit ultrices. Sed maximus dui quis feugiat iaculis. Sed et enim bibendum, lacinia quam sed, maximus magna. Donec vel quam mi. Morbi vitae massa elementum, egestas mauris vel, mollis arcu."
            ),
            StepModel(
                "Casi listo",
                "Maecenas aliquam pretium nisi non ullamcorper. Quisque dignissim urna nibh, malesuada tincidunt elit bibendum et. Proin viverra quis enim at convallis. Duis at scelerisque enim. Proin ligula massa, aliquet quis convallis in, placerat sed nulla. Mauris porttitor, tellus a viverra gravida, dui tellus mollis leo, vitae fermentum quam enim ut augue. Cras sit amet tempor nibh, id molestie sapien. Sed blandit, quam nec rutrum eleifend, tortor diam convallis nibh, ut sodales risus purus vitae leo. Nam sollicitudin, magna nec tincidunt egestas, diam justo suscipit enim, a viverra nisi nibh ut purus. Fusce in urna id augue dictum faucibus. Sed tristique tortor vitae dapibus condimentum. Integer mattis, sem ac pretium consectetur, odio lectus sagittis ipsum, quis mattis orci lectus ut tellus."
            ),
            StepModel(
                "Completado, casi",
                "Nulla facilisi. Mauris dapibus tempor volutpat. Ut lobortis massa magna, vitae faucibus elit consectetur at. Duis cursus in mi sed lacinia. Nunc vitae egestas est. Pellentesque ullamcorper fringilla mauris eu cursus. Pellentesque sollicitudin viverra diam in dictum. Vivamus eu eros et sem consequat aliquam. Morbi quis urna pellentesque, venenatis mi at, consectetur est. Ut quis enim sit amet ex molestie malesuada sollicitudin vitae nibh. Nullam euismod diam lectus, eget pellentesque eros faucibus eu."
            ),
            StepModel(
                "Finito",
                "In congue lectus velit, ac pretium arcu placerat a. Phasellus bibendum ultricies ullamcorper. Proin eget lorem aliquet, luctus purus eu, dapibus nisi. Duis vitae cursus eros, ac tempus nisl. Sed et elit vel lorem euismod suscipit. Etiam ac lorem a erat varius laoreet. Etiam mollis interdum elit eget porta. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum et ornare mi. Nam enim dolor, ultricies in laoreet non, tempor eu augue. Aenean elementum aliquam tempor. Integer consequat massa eu sapien blandit pulvinar. Praesent non lorem a purus rutrum tincidunt."
            )
        )
        vwpItems.adapter = MainAdapter(supportFragmentManager, items)

        ctvNavigation.viewPager = vwpItems
        ctvNavigation.onFinishListener = { startActivity(Intent(this, SecondaryActivity::class.java)) }
        ctvNavigation.initializeViews()


        // Enable / Disable swipe
        vwpItems.shouldSwipe = false
    }
}
