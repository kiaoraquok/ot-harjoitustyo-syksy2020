# Alustava vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksella voi pelata *Neljän suora* -nimistä peliä (engl. [*Connect Four*](https://en.wikipedia.org/wiki/Connect_Four)). Siinä pelaavat vastakkain kaksi pelaajaa, jotka vuorotellen tiputtelevat nappuloita 7x6 kokoiseen ruudukkoon ja voittaja on se, joka saa muodostettua omilla nappuloillaan neljän suoran ensimmäisenä.

## Käyttäjäroolit
Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli *normaali käyttäjä*. Jatkokehitysideana sovellukseen saatetaan lisätä toiminnallisuus olla *kirjautunut käyttäjä*, joka voi tallentaa customoituja peliasetuksia.

## Perusversion tarjoama toiminnallisuus
Perusversio keskittyy toimivan pelin kehittämiseen
- Aloitusnäkymä, mistä voi nappia painamalla aloittaa pelin
- Pelinäkymä, missä on aluksi tyhjä ruudukko
- Pelaaja voi asettaa ruudukkoon nappulansa, minkä jälkeen vuoro siirtyy toiselle pelaajalle
- Peli ilmoittaa aina kumman pelaajan vuoro on
- Pelaajien nappulat erotetaan värein
- Peli ilmoittaa, kun joku on voittanut tai peli on päättynyt tasapeliin
- Pelin lopetus kesken pelin

## Jatkokehitysideoita
- Käyttäjänimien syöttäminen, tallentaminen ja käyttäjäkohtaiset tilastot
- Pelaajat voivat valita mielivaltaisen ruudukon koon
  - Pelaajat voivat valita mielivaltaisen "voittosuoran" pituuden esim. viiden suora tarvitaan voittoon
- Äänitehosteita
- Asetuksia
  - Oletusvärien muuttaminen (ja tallentaminen config-tiedostoon)
  - Oletuskoon muuttaminen
- Käyttäjätunnuksen luonti käyttäjäkohtaisten asetusten tallentamiseen
- Pelin keskeyttäminen ja jatkaminen toisena ajankohtana
- Vihje sovellukselta seuraavaksi siirroksi
