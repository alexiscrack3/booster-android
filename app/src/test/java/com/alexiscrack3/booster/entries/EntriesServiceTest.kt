package com.alexiscrack3.booster.entries

import com.alexiscrack3.booster.entries.EntriesService.Companion.ENTRIES_PATH
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import java.lang.Exception

class EntriesServiceTest {

    @Test
    fun `getEntries should return result when call succeeds`() {
        val querySnapshot = mock<QuerySnapshot>()
        val task = mock<Task<QuerySnapshot>>()
        whenever(task.addOnSuccessListener(any())).thenAnswer { answer ->
            (answer.arguments.first() as OnSuccessListener<QuerySnapshot>).onSuccess(querySnapshot)
        }
        val collectionReference = mock<CollectionReference> {
            on { this.get() } doReturn task
        }
        val db = mock<FirebaseFirestore> {
            on { this.collection(ENTRIES_PATH) } doReturn collectionReference
        }
        val testObject = EntriesService(db)

        val testObserver = testObject.getEntries().test()

        testObserver.assertValue(querySnapshot)
    }

    @Test
    fun `getEntries should return error when call fails`() {
        val ex = Exception()
        val anotherTask = mock<Task<QuerySnapshot>>()
        val task = mock<Task<QuerySnapshot>> {
            on { this.addOnSuccessListener(any()) } doReturn anotherTask
        }
        whenever(anotherTask.addOnFailureListener(any())).thenAnswer { answer ->
            (answer.arguments.first() as OnFailureListener).onFailure(ex)
        }
        val collectionReference = mock<CollectionReference> {
            on { this.get() } doReturn task
        }
        val firestore = mock<FirebaseFirestore> {
            on { this.collection(ENTRIES_PATH) } doReturn collectionReference
        }
        val testObject = EntriesService(firestore)

        val testObserver = testObject.getEntries().test()

        testObserver.assertError(ex)
    }

    @Test
    fun `getEntry should return result when call succeeds`() {
        val documentSnapshot = mock<DocumentSnapshot>()
        val task = mock<Task<DocumentSnapshot>>()
        whenever(task.addOnSuccessListener(any())).thenAnswer { answer ->
            (answer.arguments.first() as OnSuccessListener<DocumentSnapshot>).onSuccess(documentSnapshot)
        }
        val documentReference = mock<DocumentReference> {
            on { this.get() } doReturn task
        }
        val collectionReference = mock<CollectionReference> {
            on { this.document(anyString()) } doReturn documentReference
        }
        val firestore = mock<FirebaseFirestore> {
            on { this.collection(ENTRIES_PATH) } doReturn collectionReference
        }
        val testObject = EntriesService(firestore)

        val testObserver = testObject.getEntry(anyString()).test()

        testObserver.assertValue(documentSnapshot)
    }

    @Test
    fun `getEntry should complete when call succeeds without result`() {
        val task = mock<Task<DocumentSnapshot>>()
        whenever(task.addOnSuccessListener(any())).thenAnswer { answer ->
            (answer.arguments.first() as OnSuccessListener<DocumentSnapshot>).onSuccess(null)
        }
        val documentReference = mock<DocumentReference> {
            on { this.get() } doReturn task
        }
        val collectionReference = mock<CollectionReference> {
            on { this.document(anyString()) } doReturn documentReference
        }
        val firestore = mock<FirebaseFirestore> {
            on { this.collection(ENTRIES_PATH) } doReturn collectionReference
        }
        val testObject = EntriesService(firestore)

        val testObserver = testObject.getEntry(anyString()).test()

        testObserver.assertComplete()
    }

    @Test
    fun `getEntry should return error when call fails`() {
        val ex = Exception()
        val anotherTask = mock<Task<DocumentSnapshot>>()
        val task = mock<Task<DocumentSnapshot>> {
            on { this.addOnSuccessListener(any()) } doReturn anotherTask
        }
        whenever(anotherTask.addOnFailureListener(any())).thenAnswer { answer ->
            (answer.arguments.first() as OnFailureListener).onFailure(ex)
        }
        val documentReference = mock<DocumentReference> {
            on { this.get() } doReturn task
        }
        val collectionReference = mock<CollectionReference> {
            on { this.document(anyString()) } doReturn documentReference
        }
        val firestore = mock<FirebaseFirestore> {
            on { this.collection(ENTRIES_PATH) } doReturn collectionReference
        }
        val testObject = EntriesService(firestore)

        val testObserver = testObject.getEntry(anyString()).test()

        testObserver.assertError(ex)
    }
}
