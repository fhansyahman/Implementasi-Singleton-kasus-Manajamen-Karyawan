import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Manajemen_Karyawan {
    private static Manajemen_Karyawan instance;
    private Map<String, Karyawan> karyawans;

    private Manajemen_Karyawan() {
        karyawans = new HashMap<>();
        karyawans.put("Ari Setiawan", new Karyawan("Ari Setiawan", "Software Engineer", "Engineering"));
        karyawans.put("Monica", new Karyawan("Monica", "HR Manager", "Human Resources"));
        karyawans.put("Alfonso Setiada", new Karyawan("Alfonso Setiada", "Marketing Specialist", "Marketing"));
    }

    public static synchronized Manajemen_Karyawan getInstance() {
        if (instance == null) {
            instance = new Manajemen_Karyawan();
        }
        return instance;
    }

    public boolean addKaryawan(Karyawan karyawan) {
        if (karyawans.containsKey(karyawan.getName())) {
            System.out.println("Karyawan dengan nama yang sama sudah ada. Mohon masukkan kembali.");
            return false;
        } else {
            karyawans.put(karyawan.getName(), karyawan);
            return true;
        }
    }

    public void displaykaryawans() {
        System.out.println("Daftar Karyawan:");
        for (Karyawan karyawan : karyawans.values()) {
            System.out.println(karyawan);
        }
    }

    public Karyawan findKaryawan(String name) {
        return karyawans.get(name);
    }
}

class Karyawan {
    private String name;
    private String position;
    private String department;

    public Karyawan(String name, String position, String department) {
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Nama: " + name + ", Posisi: " + position + ", Departemen: " + department;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manajemen_Karyawan manajemen_karyawan = Manajemen_Karyawan.getInstance();

        while (true) {
            manajemen_karyawan.displaykaryawans(); 
            System.out.println("Menu:");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Cari Karyawan");
            System.out.println("3. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid. Silakan masukkan nomor pilihan.");
                scanner.nextLine(); 
                continue; 
            }
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama karyawan: ");
                    String namaKaryawan = scanner.nextLine();
                    System.out.print("Masukkan posisi karyawan: ");
                    String posisiKaryawan = scanner.nextLine();
                    System.out.print("Masukkan departemen karyawan: ");
                    String departemenKaryawan = scanner.nextLine();
                    boolean added = manajemen_karyawan.addKaryawan(new Karyawan(namaKaryawan, posisiKaryawan, departemenKaryawan));
                    if (added) {
                        System.out.println("Karyawan berhasil ditambahkan!");
                    }
                    break;
                case 2:
                    System.out.print("Masukkan nama karyawan untuk mencari: ");
                    String cariNamaKaryawan = scanner.nextLine();
                    Karyawan foundKaryawan = manajemen_karyawan.findKaryawan(cariNamaKaryawan);
                    if (foundKaryawan != null) {
                        System.out.println("Detail Karyawan: " + foundKaryawan);
                    } else {
                        System.out.println("Karyawan tidak ditemukan!");
                    }
                    break;
                case 3:
                    System.out.println("Keluar...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan lagi.");
            }
        }
    }
}
