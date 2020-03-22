package com.mycompany.unicafe;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    private Kassapaate kassapaate;
    private Maksukortti kortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void kassassaOikeaMaaraRahaaAlussa() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void oikeaMaaraMaukkaitaLounaitaMyytyAlussa() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void oikeaMaaraEdullisiaLounaitaMyytyAlussa() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassasssaOikeaRahamaaraOstetunEdullisenLounaanJalkeen() {
        kassapaate.syoEdullisesti(300);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void oikeaVaihtorahaOstetunEdullisenLounaanJalkeen() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(300);
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(1));
    }

    @Test
    public void kassanRahamaaraEiMuutuKunYritetaanOstaaEdullinenLounasLiianVahallaRahalla() {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void oikeaVaihtorahaKunRahaEiRiitaEdulliseenLounaaseen() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraEiKasva() {
        kassapaate.syoEdullisesti(200);
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(0));
    }

    @Test
    public void kassasssaOikeaRahamaaraOstetunMaukkaanLounaanJalkeen() {
        kassapaate.syoMaukkaasti(600);
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(200, kassapaate.syoMaukkaasti(600));
    }

    @Test
    public void oikeaVaihtorahaOstetunMaukkaanLounaanJalkeen() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(500);
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(1));
    }

    @Test
    public void kassanRahamaaraEiMuutuKunYritetaanOstaaMaukasLounasLiianVahallaRahalla() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void oikeaVaihtorahaKunRahaEiRiitamaukkaaseenLounaaseen() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiKasva() {
        kassapaate.syoMaukkaasti(300);
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(0));
    }

    @Test
    public void oikeaVeloitusKunKortillaOnTarpeeksiRahaaEdulliseenLounaaseen() {
        kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kortti.saldo(), is(60));
    }

    @Test
    public void kassaPalauttaaTruenKunOnTarpeeksiRahaaEdulliseenLounaaseen() {
        kortti = new Maksukortti(300);
        assertThat(kassapaate.syoEdullisesti(kortti), is(true));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaKorttiostoksessa() {
        kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(1));
    }

    public void kortinRahamaaraEiMuutuJosKortillaLiianVahanRahaaEdulliseenLounaaseen() {
        kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kortti.saldo(), is(200));
    }

    public void myytyjenLounaidenMaaraEiMuutuJosKortillaLiianVahanRahaaEdulliseenLounaaseen() {
        kortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kassapaate.edullisiaLounaitaMyyty(), is(0));
    }

    @Test
    public void kassaPalauttaaFalsenKunEiOleTarpeeksiRahaaEdulliseenLounaaseen() {
        kortti = new Maksukortti(200);
        assertThat(kassapaate.syoEdullisesti(kortti), is(false));
    }

    @Test
    public void kassanRahamaaraEiMuutuMaksettaessaKortillaEdullisestaLounaasta() {
        int kassanRahat = kassapaate.kassassaRahaa();
        kortti = new Maksukortti(300);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kassapaate.kassassaRahaa(), is(kassanRahat));
    }

    @Test
    public void oikeaVeloitusKunKortillaOnTarpeeksiRahaaMaukkaaseenLounaaseen() {
        kortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(kortti);
        assertThat(kortti.saldo(), is(100));
    }

    @Test
    public void kassaPalauttaaTruenKunOnTarpeeksiRahaaMaukkaaseenLounaaseen() {
        kortti = new Maksukortti(500);
        assertThat(kassapaate.syoMaukkaasti(kortti), is(true));
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKorttiostoksessa() {
        kortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(kortti);
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(1));
    }

    public void kortinRahamaaraEiMuutuJosKortillaLiianVahanRahaaMaukkaaseenLounaaseen() {
        kortti = new Maksukortti(300);
        kassapaate.syoMaukkaasti(kortti);
        assertThat(kortti.saldo(), is(300));
    }

    public void myytyjenLounaidenMaaraEiMuutuJosKortillaLiianVahanRahaaMaukkaaseenLounaaseen() {
        kortti = new Maksukortti(300);
        kassapaate.syoMaukkaasti(kortti);
        assertThat(kassapaate.maukkaitaLounaitaMyyty(), is(0));
    }

    @Test
    public void kassaPalauttaaFalsenKunEiOleTarpeeksiRahaaMaukkaaseenLounaaseen() {
        kortti = new Maksukortti(300);
        assertThat(kassapaate.syoMaukkaasti(kortti), is(false));
    }

    @Test
    public void kassanRahamaaraEiMuutuMaksettaessaKortillaMaukkaastaLounaasta() {
        int kassanRahat = kassapaate.kassassaRahaa();
        kortti = new Maksukortti(500);
        kassapaate.syoEdullisesti(kortti);
        assertThat(kassapaate.kassassaRahaa(), is(kassanRahat));
    }

    @Test
    public void rahanLatausKasvattaaKortinSaldonOikein() {
        kortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(kortti, 200);
        assertThat(kortti.saldo(), is(700));
    }

    @Test
    public void rahanLatausKasvattaaKassanRahamaaraaOikein() {
        kortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(kortti, 200);
        assertThat(kassapaate.kassassaRahaa(), is(100200));
    }

    @Test
    public void rahanLatausNegatiivisellaMaarallaEiMuutaKortinSaldoa() {
        kortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(kortti, -200);
        assertThat(kortti.saldo(), is(500));
    }

    @Test
    public void rahanLatausNegatiivisellaMaarallaEiMuutaKassanRahamaaraa() {
        kortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(kortti, -200);
        assertThat(kassapaate.kassassaRahaa(), is(100000));
    }
}
