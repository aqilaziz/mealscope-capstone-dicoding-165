package id.aqil.mealscope.core.security

import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

object DatabasePassphraseProvider {
    fun createFactory(): SupportFactory {
        val passphrase = SQLiteDatabase.getBytes("mealscope-capstone-secure-db".toCharArray())
        return SupportFactory(passphrase)
    }
}
