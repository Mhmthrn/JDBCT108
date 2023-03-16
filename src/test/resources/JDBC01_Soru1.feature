Feature:US1001 DB de bulunan jtable bilgilerini getirme
  @wip
  Scenario Outline: TC0101 istenilen tablerdan istenilen data bilgileri yazdirilir

  Given DB ile connection saglanir
  Then  Database "<tableName>" tablosundaki "<istenilenColumn>" degeri  "<istenenDeger>" olan tüm product'lari listeler
  Then  Listelenen toplam urun sayisinin 50 den dusuk oldugunu test eder "slug" yazdirir
  And   DB ile connection kapatir.
    Examples:
      | tableName | istenilenColumn | istenenDeger |
      | seller_products | discount_type  | 1 |
     # | colors          | name         | 11 |
     # | menus           | name           | 14 |

