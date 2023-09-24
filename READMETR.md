# Basit Bankacılık ile Başlangıç (Tahmini süre 1-3 saat)

Bu görevde, herhangi bir sayıda işlemi banka hesapları için yönetebilen bir bankacılık hizmeti oluşturacaksınız. Bu hizmet, bir bankanın iç işleyişini modelleyen daha büyük bir hizmet koleksiyonunun bir parçasıdır. "banka hesabı" için hizmetler, banka hesaplarının aşırı basitleştirilmiş bir dünyada nasıl çalışabileceğini basit bir şekilde modellemelidir.
Bu görev için, banka hesabı sadece hesap sahibinin adını, hesap numarasını ve hesabın bakiyesini koruma konusunda ilgilidir. Uç noktalar, hesaba kredi verme ve hesabı borçlandırma yöntemlerini sağlama amaçlı yöntemlerle sınırlı olacaktır.
Banka hesabı nesnesi için veri modeliniz, sahibi alanını (alan türü java.lang.String), hesap numarasını (String) ve bakiyeyi (double) tutmak için alanlara sahip olmalıdır. Yukarıda belirtilen gibi credit() hizmeti, verilen miktarı alıcının Banka Hesabının bakiyesine ekler ve debit() hizmeti, verilen miktarı alıcının Banka Hesabının bakiyesinden çıkarır.
Bankacılık sistemimizin nesne modeli, işlem nesnelerini içermelidir. Bir işlem nesnesi, işlemin türünü (yatırma, çekme, ödemeler vb.) ve işlemin tarihini ve miktarını izler. Her işlem türü kendi parametrelerine sahip olacaktır. Aşağıdaki diagram, Banka Hesapları ve İşlemlerin nasıl ilişkili olduğunu göstermektedir. DepositTransaction örneği bir yatırımı temsil eder; WithdrawalTransaction bir çekmeyi temsil eder (diagramdaki üçgen miras almayı gösterir). PhoneBillPaymentTransaction, CheckTransaction vb. için miras almayı gösterilmez - bu sınıfı nereye koyacağınıza karar vermelisiniz. Tüm işlemlerin en azından tarih ve miktar alanları olmalıdır. Tarih alanı işlemin zamanını içermeli ve otomatik olarak hesaplanmalıdır.
![model](images/model.png)

## Başlangıç ​​için bir proje şablonu kullanabilirsiniz
Şablon proje (gradle Java), src klasörü altında bulunmaktadır. Uygulamanız için ana seçenekler olarak Quarkus veya Spring(boot), Junit ve JPA kullanmanızı öneririz.
## Task 1: Implement and test the model
Bu işlem nesneleri, hem Banka Hesabına finansal taleplerde bulunmak hem de bu taleplerin kaydını tutmak için kullanılacaktır. Aşağıdaki Birim testi segmenti, işlemlerin hizmet tarafında nasıl kullanılacağını göstermektedir:

    BankAccount account = new BankAccount("Jim", 12345);
    account.post(new DepositTransaction(1000));
    account.post(new WithdrawalTransaction(200));
    account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));
    assertEquals(account.getBalance(), 703.50, 0.0001)

### BONUS Task 1:  Daha İyi Bir Uygulama Alternatifi Bulun
Banka hesabı post yöntemi, her İşlem türü için özel bir şey yapmalıdır. Örneğin post(Yatırımİşlemi) ve post(Çekimİşlemi). Bu çözüm işe yarar ancak aşırı yüklenmiş yöntemlerin ailelerini oluşturmak bakım sorunlarına neden olduğundan önerilmez. Daha fazla İşlem alt sınıfı eklediğimizde BankaHesabı sınıfını sürekli değiştirmemiz gerekecektir. Nesne türüne dayalı durum ifadeleri yazmak, nesne türüne dayalı olduğundan kötü bir uygulama olarak kabul edilir. Ayrıca, aynı bakım sorunlarına sahiptir. Daha fazla İşlem alt sınıfı eklemek değişiklikler gerektirecektir. Banka hesabınızı yeni işlem türleri ekleyerek değiştirmemek için çok biçimsizlik kullanarak işlemi nasıl devredebileceğiniz bir çözüm bulun. En azından verilen birim testini çalıştırabilir hale getirmelisiniz.


## Task 2:  Spring Rest Controller kullanarak REST API sağlayın ve TEST edin
Aşağıdaki gibi bankacılık sistemi için bir REST API sağlayın. BankaHesaplarınızı yukarıdaki modelinizi bir veritabanına JPA kullanarak kaydetmek için hizmetler ve depolar kullanın. Lütfen kodunuz için testler (MOCK veya diğer) sağlayın:
Bir hesaba para yatırmak için, şunu kullanabilirsiniz:

    curl --location --request POST 'http://localhost:8080/account/v1/credit/669-7788' \
    --header 'Content-Type: application/json' \
    --header 'Accept: application/json' \
    --data-raw '    {
            "amount": 1000.0
        }'

    response would be (200):
    {
        "status": "OK",
        "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
    }

To withdraw money:Para çekmek için:

    curl --location --request POST 'http://localhost:8080/account/v1/debit/669-7788' \
    --header 'Content-Type: application/json' \
    --header 'Accept: application/json' \
    --data-raw '    {
            "amount": 50.0
        }'

    response would be (200):
    {
        "status": "OK",
        "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
    }

To get the current account data, one would use:  Mevcut hesap verilerini almak için:

    curl --location --request GET 'http://localhost:8080/account/v1/669-7788'

    response would be:

    {
        "accountNumber": "669-7788",
        "owner": "Kerem Karaca",
        "balance": 950.0,
        "createDate": "2020-03-26T06:15:50.550+0000",
        "transactions": [
            {
                "date": "2020-03-26T06:16:03.563+0000",
                "amount": 1000.0,
                "type": "DepositTransaction",
                "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
            },
            {
                "date": "2020-03-26T06:16:35.047+0000",
                "amount": 50.0,
                "type": "WithdrawalTransaction",
                "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
            }
        ]
    }


## Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/gradle-plugin/reference/html/)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Guides
The following guides illustrate how to use some features concretely:

* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)



