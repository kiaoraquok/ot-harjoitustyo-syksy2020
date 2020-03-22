package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void lataaminenKasvattaaSaldonOikein() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }

    @Test
    public void saldoVaheneeOikeinKunRahaaOnTarpeeksi() {
        kortti.otaRahaa(3);
        assertEquals(7, kortti.saldo());
    }

    @Test
    public void saldoEiMuutuKunRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void otaRahaaPalauttaaTruenKunRahatRiittavat() {
        assertEquals(true, kortti.otaRahaa(9));
    }

    @Test
    public void otaRahaaPalauttaaFalsenKunRahatEiRiita() {
        assertEquals(false, kortti.otaRahaa(11));
    }
    
    @Test
    public void toStringTulostaaOikein() {
        kortti.lataaRahaa(250);
        assertEquals("saldo: 2.60", kortti.toString());
    }
}
