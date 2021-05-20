package org.com.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.com.entity.Talon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalonRepository extends JpaRepository<Talon, Long> {
    List<Talon> findByDoctorDolznostAndUserNullAndInstitutionName(String dDolznost, String iName);

    default List<Talon> findByDoctorDolznostAndUserNullAndInstitutionNameAtTheCertainTime(String dDolznost, String iName, String date) {
        List<Talon> talons = findByDoctorDolznostAndUserNullAndInstitutionName(dDolznost, iName);
        List<Talon> result_talons = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (date.length() == 23) {
                Scanner scanner = new Scanner(date).useDelimiter("and");
                Date after, before;
                after = sdf.parse(scanner.next());
                before = sdf.parse(scanner.next());
                for (Talon talon : talons) {
                    Date talonDate = sdf.parse(new Scanner(talon.getDate().toString()).useDelimiter(" ").next());
                    if (talonDate.after(after) && talonDate.before(before) || talonDate.equals(after)
                            && talonDate.before(before) || talonDate.after(after) && talonDate.equals(before)) {
                        result_talons.add(talon);
                    }
                }
            } else {
                for (Talon talon : talons) {
                    Date talonDate = sdf.parse(new Scanner(talon.getDate().toString()).useDelimiter(" ").next());
                    if (talonDate.equals(sdf.parse(date))) {
                        result_talons.add(talon);
                    }
                }
            }
        } catch (Exception e) {
            return result_talons;
        }
        return result_talons;
    }
}
