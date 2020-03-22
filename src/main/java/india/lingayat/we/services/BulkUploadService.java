package india.lingayat.we.services;

import india.lingayat.we.exceptions.AppException;
import india.lingayat.we.models.*;
import india.lingayat.we.models.enums.*;
import india.lingayat.we.repositories.RoleRepository;
import india.lingayat.we.repositories.UserRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    public List<User> bulkUploadUsers(MultipartFile file) throws IOException, ParseException {
        List<User> usersList = new ArrayList<User>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = worksheet.iterator();

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        while(rowIterator.hasNext()){
            Row currentRow = rowIterator.next();

            User user = new User();

            user.setRoles(Collections.singleton(userRole));

            user.setFirstName(currentRow.getCell(0).toString());
            user.setMiddleName(currentRow.getCell(1).toString());
            user.setLastName(currentRow.getCell(2).toString());
            user.setEmail(currentRow.getCell(3).toString());
            user.setPassword(passwordEncoder.encode(currentRow.getCell(4).toString()));
            user.setContact(currentRow.getCell(5).toString());
            user.setUsername(currentRow.getCell(6).toString());

            UserAdditionalDetails uad = new UserAdditionalDetails();

            uad.setUser(user);
            uad.setCurrentPinCode(421301);
            uad.setPermanentPinCode(421301);

            user.setUserAdditionalDetails(uad);

            UserEducationalDetails ued = new UserEducationalDetails();

            ued.setUser(user);
            ued.setQualification(Qualification.valueOf(currentRow.getCell(7).toString()));
            ued.setInstitute(currentRow.getCell(8).toString());

            user.setUserEducationalDetails(ued);

            UserMedicalDetails umd = new UserMedicalDetails();

            umd.setUser(user);
            umd.setBloodGroup(BloodGroup.valueOf(currentRow.getCell(9).toString()));

            user.setUserMedicalDetails(umd);

            UserPersonalDetails upd = new UserPersonalDetails();

            upd.setUser(user);
            upd.setHeightInCms((int)Double.parseDouble(currentRow.getCell(10).toString()));
            upd.setWeightInKgs((int)Double.parseDouble(currentRow.getCell(11).toString()));
            upd.setComplexion(Complexion.valueOf(currentRow.getCell(12).toString()));
            upd.setGender(Gender.valueOf(currentRow.getCell(13).toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            Date date = new Date(sdf.parse(currentRow.getCell(14).toString()).getTime());
            upd.setDob(date);
            upd.setFamilyType(FamilyType.valueOf(currentRow.getCell(15).toString()));
            upd.setMaritalStatus(MaritalStatus.valueOf(currentRow.getCell(16).toString()));
            upd.setPlaceOfBirth(currentRow.getCell(17).toString());

            user.setUserPersonalDetails(upd);

            UserProfessionalDetails uprd = new UserProfessionalDetails();

            uprd.setUser(user);
            uprd.setJobType(JobType.valueOf(currentRow.getCell(18).toString()));
            uprd.setMonthlyIncome((long)Double.parseDouble(currentRow.getCell(19).toString()));

            user.setUserProfessionalDetails(uprd);



            userRepository.save(user);
            usersList.add(user);

        }

        return usersList;

    }
}
