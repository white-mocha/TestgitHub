package dev.chu.testapp.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.ToJson

data class BlackjackHand(
    var hidden_card: Card,
    var visible_cards: List<Card>
)

data class Card(
    val rank: Char,
    val suit: Suit
)

enum class Suit(name: String) {
    CLUBS("CLUBS"), DIAMONDS("DIAMONDS"), HEARTS("HEARTS"), SPADES("SPADES");
}

class CardAdapter {
    @ToJson
    fun toJson(card: Card): String {
        return (card.rank + card.suit.name.substring(0, 1))
    }

    @FromJson
    fun fromJson(card: String): Card {
        if (card.length != 2) throw JsonDataException("Unknown card: $card")
        val rank = card[0]
        return when (card[1]) {
            'C' -> Card(rank, Suit.CLUBS)
            'D' -> Card(rank, Suit.DIAMONDS)
            'H' -> Card(rank, Suit.HEARTS)
            'S' -> Card(rank, Suit.SPADES)
            else -> throw JsonDataException("unknown suit: $card")
        }
    }
}