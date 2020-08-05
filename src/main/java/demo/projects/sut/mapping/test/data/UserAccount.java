package demo.projects.sut.mapping.test.data;

import com.opencsv.bean.CsvBindByPosition;
import demo.projects.test.framework.helpers.test.data.loader.TestData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User account object.
 */
@Getter
@Setter
@ToString
@SuppressWarnings("checkstyle:MagicNumber")
public class UserAccount implements TestData {

    @CsvBindByPosition(position = 0)
    private int internalId;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String userFirstName;

    @CsvBindByPosition(position = 3)
    private String userLastName;

    @CsvBindByPosition(position = 4)
    private String email;

    @CsvBindByPosition(position = 5)
    private String password;

    @CsvBindByPosition(position = 6)
    private int dayOfBirth;

    @CsvBindByPosition(position = 7)
    private String monthOfBirth;

    @CsvBindByPosition(position = 8)
    private String yearOfBirth;

    @CsvBindByPosition(position = 9)
    private String addressFirstName;

    @CsvBindByPosition(position = 10)
    private String addressLastName;

    @CsvBindByPosition(position = 11)
    private String company;

    @CsvBindByPosition(position = 12)
    private String address;

    @CsvBindByPosition(position = 13)
    private String address2;

    @CsvBindByPosition(position = 14)
    private String city;

    @CsvBindByPosition(position = 15)
    private String state;

    @CsvBindByPosition(position = 16)
    private String zip;

    @CsvBindByPosition(position = 17)
    private String country;

    @CsvBindByPosition(position = 18)
    private String additionalDetails;

    @CsvBindByPosition(position = 19)
    private String homePhone;

    @CsvBindByPosition(position = 20)
    private String mobilePhone;

    @CsvBindByPosition(position = 21)
    private String addressAlias;

    @Override
    public int getInternalId() {
        return internalId;
    }

    /**
     * @return Full name of user.
     */
    public String getFullName() {
        return userFirstName + " " + userLastName;
    }
}
