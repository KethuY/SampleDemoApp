package com.example.sampledemoapp.utility

import com.example.sampledemoapp.utility.DemoNutHelper.filterNullOrBlank
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DemoNutHelperTest {

    @Test
    fun isApiSuccessfulTest() {
        Assert.assertTrue(DemoNutHelper.isApiSuccessful("true"))
        Assert.assertFalse(DemoNutHelper.isApiSuccessful("false"))
        Assert.assertFalse(DemoNutHelper.isApiSuccessful(""))
        Assert.assertFalse(DemoNutHelper.isApiSuccessful("null"))
        Assert.assertFalse(DemoNutHelper.isApiSuccessful("fdsjfk"))
    }

    @Test
    fun filterNullOrBlank() {
        Assert.assertEquals("", null.filterNullOrBlank())
        Assert.assertEquals("null", "null".filterNullOrBlank())
        Assert.assertEquals("", " ".filterNullOrBlank())
        Assert.assertEquals("", "".filterNullOrBlank())
        Assert.assertEquals("satya", "satya".filterNullOrBlank())
        Assert.assertEquals("12345", "12345".filterNullOrBlank())
    }

    @Test
    fun getFormattedTextTest() {
        Assert.assertEquals("Satya, Kethu", DemoNutHelper.getFormattedText("Satya","Kethu"))
        Assert.assertEquals("Satya", DemoNutHelper.getFormattedText("Satya",""))
        Assert.assertEquals("Kethu", DemoNutHelper.getFormattedText("","Kethu"))
        Assert.assertEquals("", DemoNutHelper.getFormattedText("",""))
        Assert.assertEquals("", DemoNutHelper.getFormattedText(null,null))
        Assert.assertEquals("Satya", DemoNutHelper.getFormattedText("Satya",null))
        Assert.assertEquals("Kethu", DemoNutHelper.getFormattedText(null,"Kethu"))

    }
}