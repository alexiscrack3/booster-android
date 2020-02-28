package com.alexiscrack3.booster.entries

import com.alexiscrack3.booster.models.Category
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.models.Sense
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import com.nhaarman.mockitokotlin2.any as anything

class EntriesMapperTest {
    private val id = "1"
    private val headword = "headword"
    private val category = Category.NOUN
    private val sense = Sense("this is the concept", listOf("this is an example"))
    private val senses = listOf(sense)
    private val tags = listOf("tag")

//    @Test
//    fun `map should convert query snapshot to entry`() {
//        val queryDocumentSnapshot = mock<QueryDocumentSnapshot> {
//            on { this.id } doReturn id
//            on { this.get("headword") } doReturn headword
//            on { this.get("category") } doReturn category.ordinal
//            on { this.get("senses") } doReturn listOf(
//                mapOf(
//                    "definition" to sense.definition,
//                    "examples" to listOf(sense.examples.first())
//                )
//            )
//            on { this.get("tags") } doReturn tags
//        }
//        val querySnapshot = mock<QuerySnapshot>()
//        val transform = anything<(QueryDocumentSnapshot) -> Entry>()
//        whenever(querySnapshot.map(transform)).thenAnswer { answer ->
//            (answer.arguments.first() as (QueryDocumentSnapshot) -> Entry).invoke( queryDocumentSnapshot)
//        }
//        val testObject = EntriesMapper()
//
//        val entries = testObject.map(querySnapshot)
//        val entry = entries.first()
//
//        assertThat(entry.id, equalTo(id))
//        assertThat(entry.headword, equalTo(headword))
//        assertThat(entry.category, equalTo(category))
//        assertThat(entry.senses, equalTo(senses))
//        assertThat(entry.tags, equalTo(tags))
//    }

    @Test
    fun `map should convert document snapshot to entry`() {
        val documentSnapshot = mock<DocumentSnapshot> {
            on { this.id } doReturn id
            on { this.get("headword") } doReturn headword
            on { this.get("category") } doReturn category.ordinal
            on { this.get("senses") } doReturn listOf(
                mapOf(
                    "definition" to sense.definition,
                    "examples" to listOf(sense.examples.first())
                )
            )
            on { this.get("tags") } doReturn tags
        }
        val testObject = EntriesMapper()

        val entry = testObject.map(documentSnapshot)!!

        assertThat(entry.id, equalTo(id))
        assertThat(entry.headword, equalTo(headword))
        assertThat(entry.category, equalTo(category))
        assertThat(entry.senses, equalTo(senses))
        assertThat(entry.tags, equalTo(tags))
    }
}
