package id.ac.umn.week09_29240;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private MahasiswaRepo mhsRepo;
    private LiveData<List<Mahasiswa>> mahasiswaList;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        mhsRepo = new MahasiswaRepo(application);
        mahasiswaList = mhsRepo .getDaftarMahasiswa();
    }
    LiveData<List<Mahasiswa>> getDaftarMahasiswa(){
        return this.mahasiswaList;
    }
    public void insert(Mahasiswa mhs) {
        mhsRepo.insert(mhs);
    }

    public void deleteAll() {
        mhsRepo.deleteAll();
    }
    public void delete(Mahasiswa mhs) {
        mhsRepo.delete(mhs);
    }
    public void update(Mahasiswa mhs) {
        mhsRepo.update(mhs);
    }

}
