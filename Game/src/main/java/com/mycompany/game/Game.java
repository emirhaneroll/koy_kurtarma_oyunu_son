/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.game;

/**
 *
 * @author User
 */
import java.util.*;

public class Game {
    private VillageQueue queue;
    private Inventory inventory;
    private List<Village> allVillages;

    public Game() {
        queue = new VillageQueue();
        inventory = new Inventory();
        allVillages = new ArrayList<>();

        createVillages();
    }

    private void createVillages() {
    Village v1 = new Village("Yesilvadi");
    v1.addItem(new Item("Kilic", 10));
    v1.addItem(new Item("Yiyecek", 3));
    v1.addItem(new Item("Kalkan", 7));

    Village v2 = new Village("Gumuskoy");
    v2.addItem(new Item("Buyu", 5));
    v2.addItem(new Item("Bakir", 4));
    v2.addItem(new Item("Zirh", 12));

    Village v3 = new Village("Altinsehir");
    v3.addItem(new Item("Harita", 2));
    v3.addItem(new Item("Gumus", 6));
    v3.addItem(new Item("Anahtar", 1));

    Village v4 = new Village("Demirtepe");
    v4.addItem(new Item("Balta", 8));
    v4.addItem(new Item("Kalkan", 7));
    v4.addItem(new Item("Mesale", 2));

    Village v5 = new Village("Kristalkoy");
    v5.addItem(new Item("Altin", 15));
    v5.addItem(new Item("Zirh", 12));
    v5.addItem(new Item("Ok-Yay", 9));

    Village v6 = new Village("Zumrutvadi");
    v6.addItem(new Item("Yiyecek", 3));
    v6.addItem(new Item("Anahtar", 1));
    v6.addItem(new Item("Kiliç", 10));

    Village v7 = new Village("Elmassehir");
    v7.addItem(new Item("Bakir", 4));
    v7.addItem(new Item("Mesale", 2));
    v7.addItem(new Item("Buyu", 5));

    // Sıralı olarak kuyruk ve listeye ekle
    Village[] all = {v1, v2, v3, v4, v5, v6, v7};
    for (Village v : all) {
        queue.enqueue(v);
        allVillages.add(v);
    }
}


    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Koy Kurtarma Oyunu ===");
            System.out.println("1. Koyleri Listele");
            System.out.println("2. Cantayi Goruntule");
            System.out.println("3. Koy Kurtar");
            System.out.println("4. Oge Kullan/Cikar");
            System.out.println("5. Arama Yap");
            System.out.println("6. Ilerleme Durumu");
            System.out.println("7. Cikis");
            System.out.print("\nSeciminiz (1-7): ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> listVillages();
                case "2" -> inventory.displayInventory();
                case "3" -> liberateVillage();
                case "4" -> manageItem(sc);
                case "5" -> searchItem(sc);
                case "6" -> showProgress();
                case "7" -> {
                    System.out.println("Oyun sonlandiriliyor...");
                    return;
                }
                default -> System.out.println("Gecersiz secim!");
            }
        }
    }

    private void listVillages() {
        System.out.println("\n=== Koyler ===");
        int i = 1;
        for (Village v : allVillages) {
            String status = v.isLiberated() ? "Kurtarildi" : "Kurtarilmadi";
            List<Item> items = v.getInventory().toList();
            String itemList = items.stream().map(Item::getName).reduce("", (a, b) -> a + ", " + b);
            System.out.printf("%d. %s - %s\n   Ogeler: %s\n", i++, v.getName(), status, itemList.substring(2));
        }
    }

    private void liberateVillage() {
    Village v = queue.peek();
    if (v == null) {
        System.out.println("Tum koyler kurtarildi.");
        return;
    }

    Scanner sc = new Scanner(System.in);
    String name = v.getName();

    // 5. Koy – Guc Puani Gorevi (Kristalkoy)
    if (name.equals("Kristalkoy")) {
        int requiredPower = 7;
        int totalPower = 0;

        System.out.println("Kristalkoy'e ulasmak icin en az 7 guc puanina sahip ogeler kullanmalisiniz.");
        while (totalPower < requiredPower) {
            // Canta ogeleri listele - isim ve guc puani
            System.out.println("\nCantadaki ogeler:");
            int i = 1;
            for (Item item : inventory.getItems()) {
                System.out.println(i++ + ". " + item.getName() + " (Guc: " + item.getPower() + ")");
            }

            System.out.printf("Toplam guc: %d / %d\n", totalPower, requiredPower);
            System.out.print("Kullanmak istediginiz oge adi: ");
            String useName = sc.nextLine();
            Item item = inventory.pop(useName);
            if (item != null) {
                totalPower += item.getPower();
                System.out.println(useName + " kullanildi. Guc: " + item.getPower());
            } else {
                System.out.println("Oge bulunamadi.");
            }
        }
        System.out.println("Gerekli guc toplandi. Kristalkoy kurtariliyor...");
    }

    // 6. Koy – Bulmaca (Zumrutvadi)
    else if (name.equals("Zumrutvadi")) {
        System.out.println("\nZumrutvadi'ye ulasmak icin bulmacayi cozmelisiniz.");
        System.out.println("Soru: Kristalkoy ve Zumrutvadi'deki envanterlerin bas harflerinden olusan 4 harfli kelime?");
        System.out.println("Ipucu: Kristalkoy => Altin, Zirh, Ok-Yay");
        System.out.println("Ipucu: Zumrutvadi => Yiyecek, Anahtar, Kilic");

        while (true) {
            System.out.print("Cevabiniz: ");
            String answer = sc.nextLine().toUpperCase();
            if (answer.equals("KAYA")) {
                System.out.println("Tebrikler! Bulmacayi cozdunuz.");
                break;
            } else {
                System.out.println("Yanlis cevap. Tekrar deneyin.");
            }
        }
    }

    // 7. Koy – Canta siniri (Elmassehir)
    else if (name.equals("Elmassehir")) {
        System.out.println("\nElmassehir'e girebilmek icin cantanizda en fazla 7 oge olmali.");
        while (inventory.getSize() > 7) {
            inventory.displayInventory();
            System.out.print("Cikarmak istediginiz oge adi: ");
            String remove = sc.nextLine();
            inventory.pop(remove);
        }
        System.out.println("Giris sarti saglandi.");
    }

    // Ortak kurtarma islemleri
    System.out.println("\n" + v.getName() + " koyu kurtariliyor...");
    for (Item item : v.getInventory().toList()) {
        while (!inventory.push(item)) {
            System.out.println("Canta dolu! Bir oge cikarmalisiniz.");
            inventory.displayInventory();
            System.out.print("Cikarmak istediginiz oge adi: ");
            String removeItem = sc.nextLine();
            inventory.pop(removeItem);
        }
        System.out.println("- " + item + " eklendi.");
    }

    v.liberate();
    queue.dequeue();
    System.out.println(v.getName() + " basariyla kurtarildi!");
}



    private void manageItem(Scanner sc) {
        System.out.println("\n1. Oge Kullan");
        System.out.println("2. Oge Cikar");
        System.out.print("Seciminiz: ");
        String choice = sc.nextLine();

        System.out.print("Oge adi: ");
        String itemName = sc.nextLine();

        if (choice.equals("1")) {
            boolean success = inventory.useItem(itemName);
            if (success) System.out.println(itemName + " kullanildi.");
        } else if (choice.equals("2")) {
            Item removed = inventory.pop(itemName);
            if (removed != null)
                System.out.println(itemName + " cikarildi.");
            else
                System.out.println(itemName + " bulunamadi.");
        } else {
            System.out.println("Gecersiz secim.");
        }
    }

    private void searchItem(Scanner sc) {
        System.out.println("\n1. Cantada Ara");
        System.out.println("2. Koylerde Ara");
        System.out.print("Seciminiz: ");
        String choice = sc.nextLine();

        System.out.print("Aranacak oge adi: ");
        String itemName = sc.nextLine();

        if (choice.equals("1")) {
            Item found = inventory.searchItem(itemName);
            if (found != null)
                System.out.println("Oge cantada bulundu: " + found);
            else
                System.out.println("Cantada bulunamadi.");
        } else if (choice.equals("2")) {
            List<String> foundVillages = new ArrayList<>();
            for (Village v : allVillages) {
                if (v.hasItem(itemName)) {
                    foundVillages.add(v.getName());
                }
            }
            if (!foundVillages.isEmpty()) {
                System.out.println("Oge su koylerde bulundu:");
                for (String name : foundVillages) System.out.println("- " + name);
            } else {
                System.out.println("Hicbir koyde bulunamadi.");
            }
        } else {
            System.out.println("Gecersiz secim.");
        }
    }

    private void showProgress() {
        System.out.println("\n=== Ilerleme Durumu ===");

        Village current = queue.peek();
        if (current != null)
            System.out.println("Su anki koy: " + current.getName());
        else
            System.out.println("Tum koyler kurtarildi.");

        System.out.println("\nKurtarilan koyler:");
        for (Village v : allVillages) {
            if (v.isLiberated())
                System.out.println("- " + v.getName());
        }

        System.out.println("\nKurtarilacak koyler:");
        for (Village v : allVillages) {
            if (!v.isLiberated())
                System.out.println("- " + v.getName());
        }

        long liberatedCount = allVillages.stream().filter(Village::isLiberated).count();
        System.out.printf("\nToplam Ilerleme: %d/7 koy kurtarildi.\n", liberatedCount);
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
