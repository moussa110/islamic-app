package mahmoud.moussa.myquranapplication.apiManager

import mahmoud.moussa.myquranapplication.apiManager.model.RadioResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("radios/radio_arabic.json")
    fun getRadioChannel():Call<RadioResponse>;
}