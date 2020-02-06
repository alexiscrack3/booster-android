package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import com.nhaarman.mockitokotlin2.any as anything

class EntriesMapperTest {

    @Test
    fun `map should convert query snapshot to entry`() {
        val id = "1"
        val headword = "headword"
        val `class` = "class"
        val pronunciation = "pronunciation"
        val definitions = emptyList<String>()
        val queryDocumentSnapshot = mock<QueryDocumentSnapshot> {
            on { this.id } doReturn id
            on { this.get("headword") } doReturn headword
            on { this.get("class") } doReturn `class`
            on { this.get("pronunciation") } doReturn pronunciation
            on { this.get("definitions") } doReturn definitions
        }
        val querySnapshot = mock<QuerySnapshot>()
        whenever(querySnapshot.map<QueryDocumentSnapshot, Entry>(anything())).thenAnswer { answer ->
            (answer.arguments.first() as (QueryDocumentSnapshot) -> Entry).invoke(queryDocumentSnapshot)
        }
        val testObject = EntriesMapper()

        val entries = testObject.map(querySnapshot)
        val entry = entries.first()

        assertThat(entry.id, equalTo(id))
        assertThat(entry.headword, equalTo(headword))
        assertThat(entry.`class`, equalTo(`class`))
        assertThat(entry.definitions, equalTo(definitions))
    }

    @Test
    fun `map should convert document snapshot to entry`() {
        val id = "1"
        val headword = "headword"
        val `class` = "class"
        val pronunciation = "pronunciation"
        val definitions = emptyList<String>()
        val documentSnapshot = mock<DocumentSnapshot> {
            on { this.id } doReturn id
            on { this.get("headword") } doReturn headword
            on { this.get("class") } doReturn `class`
            on { this.get("pronunciation") } doReturn pronunciation
            on { this.get("definitions") } doReturn definitions
        }
        val testObject = EntriesMapper()

        val entry = testObject.map(documentSnapshot)!!

        assertThat(entry.id, equalTo(id))
        assertThat(entry.headword, equalTo(headword))
        assertThat(entry.`class`, equalTo(`class`))
        assertThat(entry.definitions, equalTo(definitions))
    }
}
