import java.util.Scanner;

public class Magang {
    static Scanner s = new Scanner(System.in);
    static String[][] dataMagang = new String[20][6];
    static int jumlahData = 0;

    public static void main(String[] args) {
        int pilihan;

        do {
            System.out.println("\n=== Sistem Pendaftaran Magang Mahasiswa ===");
            System.out.println("1. Tambah Data Magang");
            System.out.println("2. Tampilkan Semua Pendaftar Magang");
            System.out.println("3. Cari Pendaftar berdasarkan Program Studi");
            System.out.println("4. Hitung Jumlah Pendaftar untuk Setiap Status");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu (1-5): ");
            pilihan = s.nextInt();
            s.nextLine();

            switch (pilihan) {
                case 1 -> tambahData();
                case 2 -> tampilkanSemua();
                case 3 -> cariProdi();
                case 4 -> hitungStatus();
                case 5 -> System.out.println("Terima kasih!");
                default -> System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 5);
    }

    static void tambahData() {
        if (jumlahData >= 20) {
            System.out.println("Kuota penyimpanan penuh!");
            return;
        }

        System.out.print("Nama Mahasiswa: ");
        dataMagang[jumlahData][0] = s.nextLine();

        System.out.print("NIM: ");
        dataMagang[jumlahData][1] = s.nextLine();

        System.out.print("Program Studi: ");
        dataMagang[jumlahData][2] = s.nextLine();

        System.out.print("Perusahaan Tujuan Magang: ");
        dataMagang[jumlahData][3] = s.nextLine();

        String semester;
        do {
            System.out.print("Semester pengambilan magang (6 atau 7): ");
            semester = s.nextLine();
            if (!semester.equals("6") && !semester.equals("7")) {
                System.out.println(" Semester tidak valid! Hanya 6 atau 7.");
            }
        } while (!semester.equals("6") && !semester.equals("7"));
        dataMagang[jumlahData][4] = semester;

        String status;
        do {
            System.out.print("Status magang (Diterima/Menunggu/Ditolak): ");
            status = s.nextLine();
            if (!status.equalsIgnoreCase("Diterima") &&
                    !status.equalsIgnoreCase("Menunggu") &&
                    !status.equalsIgnoreCase("Ditolak")) {
                System.out.println("Status tidak valid!");
            }
        } while (!status.equalsIgnoreCase("Diterima") &&
                !status.equalsIgnoreCase("Menunggu") &&
                !status.equalsIgnoreCase("Ditolak"));
        dataMagang[jumlahData][5] = status;

        jumlahData++;
        System.out.println("Data pendaftaran magang berhasil ditambahkan. Total pendaftar: " + jumlahData);

    }

    static void tampilkanSemua() {
        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        System.out.println("\n=== Daftar Semua Pendaftar Magang ===");
        System.out.printf("%-3s %-15s %-12s %-15s %-20s %-10s %-10s\n",
                "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
        System.out.println(
                "-----------------------------------------------------------------------------------------------");

        String[] format = {
                "%-15s ", // Nama
                "%-12s ", // NIM
                "%-15s ", // Prodi
                "%-20s ", // Perusahaan
                "%-10s ", // Semester
                "%-10s " // Status
        };
        for (int i = 0; i < jumlahData; i++) {
            System.out.printf("%-3d ", (i + 1));
            for (int j = 0; j < 6; j++) {
                System.out.printf(format[j], dataMagang[i][j]);
            }
            System.out.println();
        }

    }

    static void cariProdi() {
        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
            return;
        }

        System.out.print("Masukkan Program Studi: ");
        String prodi = s.nextLine();
        boolean ditemukan = false;

        System.out.println("\n=== Data Pendaftar Prodi: " + prodi + " ===");
        System.out.printf("%-3s %-15s %-12s %-20s %-10s %-10s\n",
                "No", "Nama", "NIM", "Prodi", "Perusahaan", "Semester", "Status");
        System.out.println("-------------------------------------------------------------------------------");

        String[] format = {
                "%-15s ", // Nama
                "%-12s ", // NIM
                "%-15s ", // Prodi
                "%-20s ", // Perusahaan
                "%-10s ", // Semester
                "%-10s " // Status
        };

        for (int i = 0; i < jumlahData; i++) {
            if (dataMagang[i][2].equalsIgnoreCase(prodi)) {
                System.out.printf("%-3d ", (i + 1));

                for (int j = 0; j < 6; j++) {
                    System.out.printf(format[j], dataMagang[i][j]);
                }
                System.out.println();
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Tidak ada pendaftar dari prodi tersebut.");
        }

    }

    static void hitungStatus() {
       int diterima = 0, menunggu = 0, ditolak = 0;

        if (jumlahData == 0) {
            System.out.println("Belum ada pendaftar.");
        } else {
            for (int i = 0; i < jumlahData; i++) {
                switch (dataMagang[i][5].toLowerCase()) {
                    case "diterima" -> diterima++;
                    case "menunggu" -> menunggu++;
                    case "ditolak" -> ditolak++;
                }
            }

            System.out.println("\n=== Rekapitulasi Status Magang ===");
            System.out.println("Diterima : " + diterima);
            System.out.println("Menunggu : " + menunggu);
            System.out.println("Ditolak  : " + ditolak);
            System.out.println("Total pendaftar: " + jumlahData);
        }
    }
  
    
}
