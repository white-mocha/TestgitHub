package dev.chu.testapp

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.chu.testapp.util.BlackjackHand
import dev.chu.testapp.util.Card
import dev.chu.testapp.util.CardAdapter
import dev.chu.testapp.util.Suit
import org.junit.Test


class TestMoshi {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Test
    fun testMoshi() {
        val json = "{\n" +
                "  \"hidden_card\": \"6S\",\n" +
                "  \"visible_cards\": [\n" +
                "    \"4C\",\n" +
                "    \"AH\"\n" +
                "  ]\n" +
                "}"


        val moshi = Moshi.Builder()
            .add(CardAdapter())
            .build()

        val jsonAdapter = moshi.adapter(BlackjackHand::class.java)
        val blackjackHand = jsonAdapter.fromJson(json)
        println(blackjackHand)
    }

    @Test
    fun testMoshiFromJson() {
        val json = "{\n" +
                "  \"hidden_card\": {\n" +
                "    \"rank\": \"6\",\n" +
                "    \"suit\": \"SPADES\"\n" +
                "  },\n" +
                "  \"visible_cards\": [\n" +
                "    {\n" +
                "      \"rank\": \"4\",\n" +
                "      \"suit\": \"CLUBS\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"rank\": \"A\",\n" +
                "      \"suit\": \"HEARTS\"\n" +
                "    }\n" +
                "  ]\n" +
                "}"

        val jsonAdapter: JsonAdapter<BlackjackHand> = moshi.adapter(BlackjackHand::class.java)

        val blackjackHand = jsonAdapter.fromJson(json)
        println(blackjackHand is BlackjackHand)
    }

    @Test
    fun testMoshiToJson() {
        val blackjackHand = BlackjackHand(
            Card('6', Suit.SPADES),
            listOf(Card('4', Suit.CLUBS), Card('A', Suit.HEARTS))
        )

        val jsonAdapter = moshi.adapter(BlackjackHand::class.java)

        val json = jsonAdapter.toJson(blackjackHand)
        println(json)
    }
}