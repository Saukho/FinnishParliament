package finnish.parliament.data.api
/*
*08.03.2022
* Sami Sihvonen
* 2110603
* Retrofit builder*
 */

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import finnish.parliament.data.model.MemberOfParliament
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/*setting base url*/
/*setting base url*/
private const val BASE_URL = "https://users.metropolia.fi/~peterh/"
/*created moshi builder for kotlin json adapter factory*/
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
/*declaring retrofit*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/*parliament api service for fetching data from server*/
interface ParliamentApiService {
    @GET("mps.json") // end of API
    suspend fun getMembers(): List<MemberOfParliament>
}
/*create retrofit service by lazy*/
object ParliamentApi {
    val retrofitService : ParliamentApiService by lazy {
        retrofit.create(ParliamentApiService::class.java) }
}