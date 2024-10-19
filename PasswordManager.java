package assn07;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "password123";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int code = Math.abs(key.hashCode()) % 50;
        if (_passwords[code] == null) {
            _passwords[code] = new Account(key, value);
        }
        else {
            Account head = _passwords[code];
            while ((head.getNext() != null) && (!head.getWebsite().equals(key))) {
                head = head.getNext();
            }
            if (head.getWebsite().equals(key)) {
                head.setPassword(value);
            }
            else head.setNext(new Account(key, value));
        }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int code = Math.abs(key.hashCode()) % 50;
        if (_passwords[code] == null) {
            return null;
        }
        while (_passwords[code].getNext() != null) {
            if (_passwords[code].getWebsite().equals(key)) {
                return (V) _passwords[code].getPassword();
            }
            _passwords[code] = _passwords[code].getNext();
        }
        if (_passwords[code].getWebsite().equals(key)) {
            return (V) _passwords[code].getPassword();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < _passwords.length; i++) {
            Account object = _passwords[i];
            while (object != null) {
                size++;
                object = object.getNext();
                }
            }
        return size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        TreeSet<K> TS = new TreeSet<>();
        for (int i = 0; i < _passwords.length; i++) {
            Account object = _passwords[i];
            while (object != null) {
                TS.add((K) object.getWebsite());
                object = object.getNext();
            }
        }
        return TS;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int code = Math.abs(key.hashCode()) % 50;
        Account head = _passwords[code];
        Account tail = null;
        while (head != null) {
            if (head.getWebsite().equals(key)) {
                V removed = (V) head.getPassword();
                if (tail == null) {
                    _passwords[code] = head.getNext();
                }
                else {
                    tail.setNext(head.getNext());
                }
                return removed;
            }
            else {
                tail = head;
                head = head.getNext();
            }
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> returnedList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Account object = _passwords[i];
            while (object != null) {
                if (object.getPassword().equals(value)) {
                    returnedList.add((K)object.getWebsite());
                }
                object = object.getNext();
            }
        }
        return returnedList;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
