/*
 * Copyright 2021 Wiyono Aten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.repositories

import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models.Puppy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private val FIXED_LIST = listOf(
    Puppy(
        "1", "Shinu", "Shiba Inu", OffsetDateTime.parse("2009-01-09T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Taka_Shiba.jpg/440px-Taka_Shiba.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sed lectus vestibulum mattis ullamcorper velit sed. Nam aliquam sem et tortor consequat. Id cursus metus aliquam eleifend. Purus viverra accumsan in nisl. Egestas pretium aenean pharetra magna ac placerat vestibulum. Eget nulla facilisi etiam dignissim. Et egestas quis ipsum suspendisse ultrices. Ut morbi tincidunt augue interdum. At consectetur lorem donec massa sapien faucibus et molestie. Risus at ultrices mi tempus imperdiet nulla.

Netus et malesuada fames ac turpis egestas maecenas. Imperdiet dui accumsan sit amet nulla facilisi morbi tempus. Morbi tempus iaculis urna id volutpat. Nibh sit amet commodo nulla facilisi nullam vehicula ipsum a. Vestibulum lectus mauris ultrices eros in cursus turpis massa. Volutpat diam ut venenatis tellus in metus vulputate eu. Amet consectetur adipiscing elit duis tristique sollicitudin nibh. Nunc faucibus a pellentesque sit amet porttitor eget dolor morbi. Orci dapibus ultrices in iaculis nunc sed augue lacus viverra. Cras semper auctor neque vitae."""
    ),

    Puppy(
        "2", "Sheppy", "German Shepherd", OffsetDateTime.parse("2016-01-01T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/German_Shepherd_-_DSC_0346_%2810096362833%29.jpg/440px-German_Shepherd_-_DSC_0346_%2810096362833%29.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet cursus sit amet dictum sit amet justo donec. Fermentum leo vel orci porta non. Vel turpis nunc eget lorem dolor. Neque aliquam vestibulum morbi blandit cursus risus at. Ut eu sem integer vitae justo eget. Ut aliquam purus sit amet luctus. Maecenas volutpat blandit aliquam etiam erat velit scelerisque in. Vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt. Suspendisse sed nisi lacus sed. Diam in arcu cursus euismod. Fames ac turpis egestas integer eget. Quisque egestas diam in arcu. Rhoncus mattis rhoncus urna neque viverra.

Facilisis volutpat est velit egestas dui id ornare. Id eu nisl nunc mi ipsum faucibus. Pellentesque diam volutpat commodo sed egestas egestas fringilla phasellus. Interdum velit laoreet id donec ultrices tincidunt. Habitant morbi tristique senectus et netus et malesuada fames ac. Viverra orci sagittis eu volutpat odio facilisis mauris sit. Non nisi est sit amet facilisis magna etiam tempor orci. Vulputate enim nulla aliquet porttitor lacus luctus accumsan tortor. Mauris in aliquam sem fringilla ut morbi tincidunt. Nascetur ridiculus mus mauris vitae ultricies."""
    ),

    Puppy(
        "3", "Poodie", "Poodle", OffsetDateTime.parse("2015-12-23T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Full_attention_%288067543690%29.jpg/440px-Full_attention_%288067543690%29.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Sit amet massa vitae tortor condimentum lacinia. Interdum posuere lorem ipsum dolor sit. Fusce id velit ut tortor pretium. Pellentesque eu tincidunt tortor aliquam nulla facilisi cras fermentum odio. Cras ornare arcu dui vivamus arcu. Id neque aliquam vestibulum morbi blandit. Dignissim diam quis enim lobortis scelerisque fermentum dui. Sem viverra aliquet eget sit. Tincidunt augue interdum velit euismod in pellentesque massa placerat duis. Varius quam quisque id diam vel. Euismod quis viverra nibh cras. Quam lacus suspendisse faucibus interdum posuere lorem ipsum dolor. Gravida neque convallis a cras semper auctor neque vitae tempus. Arcu ac tortor dignissim convallis aenean et tortor at risus. In hendrerit gravida rutrum quisque non tellus orci ac. Magna eget est lorem ipsum. Augue ut lectus arcu bibendum at varius vel. Lacus viverra vitae congue eu consequat ac felis donec et.

Arcu non odio euismod lacinia at quis risus sed vulputate. Sapien eget mi proin sed libero enim sed. Commodo sed egestas egestas fringilla phasellus faucibus scelerisque eleifend donec. Ut morbi tincidunt augue interdum velit. In cursus turpis massa tincidunt dui. Faucibus ornare suspendisse sed nisi lacus sed viverra. Massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada proin. Massa tempor nec feugiat nisl pretium fusce id. Commodo ullamcorper a lacus vestibulum sed arcu non. Imperdiet dui accumsan sit amet nulla facilisi morbi tempus. Dui nunc mattis enim ut tellus elementum sagittis. Ac auctor augue mauris augue neque gravida in. In ante metus dictum at. Interdum varius sit amet mattis vulputate. Dolor magna eget est lorem ipsum dolor sit amet. In aliquam sem fringilla ut morbi tincidunt. Quis auctor elit sed vulputate mi sit."""
    ),

    Puppy(
        "4", "Hus-hus", "Siberian Husky", OffsetDateTime.parse("2007-08-08T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a3/Black-Magic-Big-Boy.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Gravida dictum fusce ut placerat orci nulla pellentesque. Massa sapien faucibus et molestie ac. Nulla porttitor massa id neque aliquam vestibulum morbi. Feugiat nisl pretium fusce id velit ut tortor pretium viverra. At volutpat diam ut venenatis tellus. Etiam sit amet nisl purus in mollis nunc. Sit amet mattis vulputate enim nulla aliquet porttitor lacus luctus. Elit ut aliquam purus sit amet luctus venenatis lectus. Orci eu lobortis elementum nibh tellus molestie nunc non blandit. Pharetra magna ac placerat vestibulum lectus mauris ultrices eros in. Maecenas accumsan lacus vel facilisis volutpat est velit egestas. Faucibus turpis in eu mi bibendum neque. Duis ultricies lacus sed turpis tincidunt id. Porta nibh venenatis cras sed felis eget. Quam elementum pulvinar etiam non quam lacus suspendisse faucibus. Diam maecenas sed enim ut sem viverra. Mattis enim ut tellus elementum sagittis.

Ipsum nunc aliquet bibendum enim facilisis gravida neque convallis a. Aenean pharetra magna ac placerat vestibulum lectus. Nisl vel pretium lectus quam. Urna nunc id cursus metus. Nec ullamcorper sit amet risus nullam eget. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh. Mauris in aliquam sem fringilla ut. In fermentum posuere urna nec tincidunt praesent. Semper feugiat nibh sed pulvinar proin. Venenatis lectus magna fringilla urna porttitor rhoncus dolor purus non. Amet dictum sit amet justo. Id porta nibh venenatis cras sed felis eget velit aliquet. Eget dolor morbi non arcu risus quis. Diam ut venenatis tellus in metus vulputate. Amet dictum sit amet justo donec enim diam vulputate ut. Tristique senectus et netus et malesuada fames ac. Mattis ullamcorper velit sed ullamcorper morbi tincidunt ornare."""
    ),

    Puppy(
        "5", "Bul-bul", "Bulldog", OffsetDateTime.parse("2018-05-23T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Bulldog_adult_male.jpg/440px-Bulldog_adult_male.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Integer malesuada nunc vel risus commodo viverra. Vulputate eu scelerisque felis imperdiet proin fermentum leo. Cras sed felis eget velit aliquet. Faucibus et molestie ac feugiat sed. In nibh mauris cursus mattis molestie. Id interdum velit laoreet id donec ultrices. Vitae congue mauris rhoncus aenean vel. Tempus urna et pharetra pharetra massa massa. Natoque penatibus et magnis dis parturient montes nascetur. Cras adipiscing enim eu turpis. Id ornare arcu odio ut sem nulla pharetra diam sit. Tempor nec feugiat nisl pretium fusce id velit. Nec ultrices dui sapien eget mi proin sed libero enim. Dui faucibus in ornare quam viverra orci. Turpis in eu mi bibendum neque egestas congue quisque.

Condimentum mattis pellentesque id nibh. Sit amet porttitor eget dolor morbi non arcu risus quis. Donec massa sapien faucibus et molestie. Porta non pulvinar neque laoreet suspendisse. Eget aliquet nibh praesent tristique magna sit. Nulla facilisi morbi tempus iaculis. Id interdum velit laoreet id donec ultrices tincidunt. Orci porta non pulvinar neque. Ornare arcu dui vivamus arcu felis bibendum. Ultrices dui sapien eget mi. Sit amet porttitor eget dolor morbi non arcu risus. In dictum non consectetur a erat nam at lectus."""
    ),

    Puppy(
        "6", "Chi-chi", "Chihuahua", OffsetDateTime.parse("2019-03-03T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4c/Chihuahua1_bvdb.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. In vitae turpis massa sed elementum. Euismod quis viverra nibh cras pulvinar mattis. Volutpat odio facilisis mauris sit amet massa vitae tortor condimentum. Condimentum mattis pellentesque id nibh tortor id aliquet lectus. Viverra mauris in aliquam sem fringilla ut morbi tincidunt augue. Diam ut venenatis tellus in metus vulputate. Venenatis tellus in metus vulputate eu scelerisque felis imperdiet proin. Vitae congue eu consequat ac felis donec et. Bibendum est ultricies integer quis auctor elit sed vulputate mi. Vel turpis nunc eget lorem dolor sed. Ligula ullamcorper malesuada proin libero nunc consequat interdum varius sit.

Tincidunt ornare massa eget egestas. Mauris cursus mattis molestie a. Amet consectetur adipiscing elit ut aliquam purus sit amet. Nullam non nisi est sit. Condimentum mattis pellentesque id nibh. Sollicitudin aliquam ultrices sagittis orci a scelerisque purus. A erat nam at lectus. Et ultrices neque ornare aenean euismod elementum nisi quis. Vel quam elementum pulvinar etiam non quam. Nec ullamcorper sit amet risus nullam eget. Egestas purus viverra accumsan in nisl nisi. Turpis nunc eget lorem dolor sed viverra ipsum nunc aliquet. Tortor consequat id porta nibh venenatis cras sed felis. Faucibus a pellentesque sit amet. Orci sagittis eu volutpat odio facilisis mauris sit amet. Integer quis auctor elit sed vulputate mi sit amet mauris. Et pharetra pharetra massa massa. Luctus venenatis lectus magna fringilla. A erat nam at lectus urna duis convallis convallis tellus."""
    ),

    Puppy(
        "7", "Pom-pom", "Pomeranian", OffsetDateTime.parse("2006-05-11T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Pomeranian.JPG/440px-Pomeranian.JPG",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Mi quis hendrerit dolor magna eget. Mauris nunc congue nisi vitae. Morbi tristique senectus et netus et malesuada fames ac. Eu mi bibendum neque egestas congue quisque egestas diam. Nec nam aliquam sem et. Ut tortor pretium viverra suspendisse potenti nullam. Integer enim neque volutpat ac tincidunt vitae semper quis lectus. Tortor posuere ac ut consequat semper viverra nam. Sed adipiscing diam donec adipiscing tristique risus nec feugiat. Id venenatis a condimentum vitae sapien pellentesque habitant morbi. Ipsum nunc aliquet bibendum enim facilisis gravida neque convallis. Ultrices dui sapien eget mi proin. Purus sit amet luctus venenatis lectus magna. Et malesuada fames ac turpis egestas integer. Nullam eget felis eget nunc lobortis mattis aliquam faucibus purus. Pharetra massa massa ultricies mi quis hendrerit.

Lobortis scelerisque fermentum dui faucibus in ornare quam viverra. Lacus vel facilisis volutpat est velit egestas dui id. Aliquam purus sit amet luctus venenatis lectus. Dignissim suspendisse in est ante in nibh mauris cursus. Ac odio tempor orci dapibus ultrices in iaculis nunc sed. Maecenas volutpat blandit aliquam etiam erat velit scelerisque. Facilisi morbi tempus iaculis urna id volutpat. Quisque id diam vel quam elementum. Lorem ipsum dolor sit amet consectetur adipiscing elit duis. Sit amet aliquam id diam maecenas ultricies mi eget. Proin libero nunc consequat interdum. Eros donec ac odio tempor orci dapibus ultrices in. Non diam phasellus vestibulum lorem sed risus ultricies tristique. Dictum varius duis at consectetur lorem donec massa. Volutpat est velit egestas dui id. Eget dolor morbi non arcu risus quis varius quam quisque. In vitae turpis massa sed elementum tempus egestas. Tortor aliquam nulla facilisi cras fermentum odio. Elit pellentesque habitant morbi tristique senectus. Sed libero enim sed faucibus."""
    ),

    Puppy(
        "8", "Goldie", "Golden Retriever", OffsetDateTime.parse("2012-10-06T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Golden_Retriever_Carlos_%2810581910556%29.jpg/440px-Golden_Retriever_Carlos_%2810581910556%29.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim nulla aliquet porttitor lacus luctus. Dignissim cras tincidunt lobortis feugiat vivamus. Ornare arcu dui vivamus arcu felis. Ut sem viverra aliquet eget sit amet. At volutpat diam ut venenatis tellus. Vestibulum lorem sed risus ultricies tristique. Eu facilisis sed odio morbi quis commodo odio aenean. Leo integer malesuada nunc vel risus commodo viverra. Id aliquet risus feugiat in ante metus dictum at tempor. Phasellus faucibus scelerisque eleifend donec pretium vulputate sapien nec sagittis. Eu nisl nunc mi ipsum. Vulputate ut pharetra sit amet aliquam id. Semper quis lectus nulla at volutpat diam. Enim blandit volutpat maecenas volutpat blandit aliquam etiam erat. Nulla aliquet porttitor lacus luctus accumsan tortor.

Viverra nibh cras pulvinar mattis nunc sed. Ultrices dui sapien eget mi proin. Tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius. Diam sit amet nisl suscipit adipiscing bibendum est ultricies. Nisi lacus sed viverra tellus in hac habitasse platea dictumst. Mauris augue neque gravida in fermentum et sollicitudin ac. Lorem mollis aliquam ut porttitor. Nunc pulvinar sapien et ligula ullamcorper malesuada proin libero nunc. Leo vel fringilla est ullamcorper eget nulla facilisi etiam. Molestie at elementum eu facilisis sed. Enim tortor at auctor urna. Ultrices in iaculis nunc sed augue lacus viverra vitae congue. At varius vel pharetra vel turpis nunc eget. In fermentum posuere urna nec tincidunt. Eu sem integer vitae justo eget. Accumsan tortor posuere ac ut. Tortor condimentum lacinia quis vel eros donec ac. Nulla facilisi cras fermentum odio eu feugiat. Ridiculus mus mauris vitae ultricies leo integer malesuada nunc vel. Quis enim lobortis scelerisque fermentum dui faucibus in."""
    ),

    Puppy(
        "9", "Dalmie", "Dalmation", OffsetDateTime.parse("2015-01-10T00:00+00:00"),
        photoUrl = "https://vetstreet-brightspot.s3.amazonaws.com/ee/140380a73111e0a0d50050568d634f/file/Dalmatian-2-645mk062311.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. In ornare quam viverra orci sagittis eu volutpat. Amet volutpat consequat mauris nunc congue nisi. Nec feugiat in fermentum posuere. Sed enim ut sem viverra aliquet eget. Eget egestas purus viverra accumsan in nisl nisi scelerisque. Lorem ipsum dolor sit amet. Lectus mauris ultrices eros in cursus turpis massa tincidunt dui. Augue eget arcu dictum varius duis. Ornare arcu dui vivamus arcu felis bibendum ut tristique. Mi in nulla posuere sollicitudin aliquam ultrices sagittis. Mauris vitae ultricies leo integer. Mattis rhoncus urna neque viverra justo nec. Ut faucibus pulvinar elementum integer enim. Fringilla ut morbi tincidunt augue. Dictum at tempor commodo ullamcorper a. At consectetur lorem donec massa sapien faucibus et molestie. Dolor sit amet consectetur adipiscing elit duis. Ornare lectus sit amet est placerat in egestas erat imperdiet. Mauris augue neque gravida in fermentum.

Congue nisi vitae suscipit tellus mauris a diam maecenas sed. Nulla pellentesque dignissim enim sit. Mi sit amet mauris commodo. Sit amet consectetur adipiscing elit ut aliquam purus. Adipiscing elit duis tristique sollicitudin nibh. Lorem mollis aliquam ut porttitor. Vitae justo eget magna fermentum. Sit amet volutpat consequat mauris nunc congue nisi vitae. Magna fermentum iaculis eu non diam phasellus. Nulla facilisi morbi tempus iaculis urna id volutpat. Id neque aliquam vestibulum morbi blandit cursus risus at. Sed libero enim sed faucibus turpis in eu mi. Facilisis volutpat est velit egestas dui. Eleifend mi in nulla posuere."""
    ),

    Puppy(
        "10", "Dobbie", "Dobermann", OffsetDateTime.parse("2012-11-07T00:00+00:00"),
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Dobermann_handling.jpg/440px-Dobermann_handling.jpg",
        description = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Morbi non arcu risus quis. Nunc eget lorem dolor sed viverra ipsum. Diam quam nulla porttitor massa id neque aliquam vestibulum. Magnis dis parturient montes nascetur ridiculus mus mauris. Ultricies integer quis auctor elit sed vulputate mi sit. Lorem dolor sed viverra ipsum nunc aliquet bibendum enim. Pretium lectus quam id leo in vitae turpis massa sed. Amet consectetur adipiscing elit ut aliquam. Convallis a cras semper auctor neque vitae tempus. Mauris in aliquam sem fringilla ut. Quam pellentesque nec nam aliquam sem et tortor consequat. Quam vulputate dignissim suspendisse in est ante. Fermentum posuere urna nec tincidunt praesent semper feugiat nibh sed. Sed faucibus turpis in eu mi bibendum neque egestas. Neque volutpat ac tincidunt vitae semper quis. Bibendum at varius vel pharetra vel turpis nunc eget lorem. Justo laoreet sit amet cursus sit.

Posuere urna nec tincidunt praesent semper feugiat nibh. Consectetur purus ut faucibus pulvinar. Morbi tincidunt ornare massa eget egestas purus. Imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada. Purus sit amet luctus venenatis lectus magna fringilla. Quam id leo in vitae turpis massa sed. Vitae nunc sed velit dignissim sodales. Iaculis eu non diam phasellus. At erat pellentesque adipiscing commodo. Sed risus pretium quam vulputate dignissim suspendisse. Tellus in metus vulputate eu scelerisque. Enim facilisis gravida neque convallis a. Accumsan lacus vel facilisis volutpat est velit egestas dui. Mattis enim ut tellus elementum sagittis vitae et. Sem viverra aliquet eget sit amet tellus cras. Morbi leo urna molestie at. Gravida cum sociis natoque penatibus et magnis dis parturient montes. Aliquam vestibulum morbi blandit cursus."""
    ),
)

class PuppyRepository {

    suspend fun loadPuppyList(): List<Puppy> = suspendCoroutine { continuation ->
        var isFirstTime = true
        CoroutineScope(continuation.context).launch {
            delay(if (isFirstTime) 1000 else 300)
            isFirstTime = false
            continuation.resume(FIXED_LIST)
        }
    }

    suspend fun loadPuppyDetails(id: String): Puppy = suspendCoroutine { continuation ->
        CoroutineScope(continuation.context).launch {
            delay(400)
            FIXED_LIST.find { it.id == id }?.let {
                continuation.resume(it)
            } ?: continuation.resumeWithException(Exception("Something wrong. Puppy with requested id does not exist!"))
        }
    }
}
