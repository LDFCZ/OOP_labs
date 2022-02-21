#include "gtest/gtest.h"
#include "Trit.h"
#include "TritSet.h"

#include <iostream>

using namespace TernaryLogic;

TritSet* tritSetFromString(std::string str) {
    if (!str.length()) {
        return new TritSet();
    }

    TritSet* set = new TritSet(str.length());
    std::transform(str.begin(), str.end(), str.begin(), ::tolower);

    size_t pos = 0;
    for (char c : str) {
        switch (c) {
        case 'f':
            (*set)[pos] = Trit(FALSE);
            break;
        case 'u':
            (*set)[pos] = Trit(UNKNOWN);
            break;
        case 't':
            (*set)[pos] = Trit(TRUE);
            break;
        default:
            delete set;
            return new TritSet();
        }
        pos++;
    }

    return set;
}


TEST(ConstructorTritSetTest, SettingDefaultValue) {
    TritSet set(100);

    for (size_t i = 0; i < 100; i++) {
        set[i] = TRUE;
    }
    
    ASSERT_EQ(set.size(), 100);
    ASSERT_GE(set.capacity(), 100);

    for (size_t i = 0; i < 100; i++) {
        ASSERT_EQ((Trit)set[i], TRUE);
    }
}

TEST(ConstructorTritSetTest, ZeroMemory) {
    TritSet set;

    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
}

TEST(ConstructorTritSetTest, AllocatingMemory) {
    TritSet set(100);

    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 100);
}

TEST(OperatorsTritSetTest, OperatorGet) {
    TritSet set;
    set[0] = TRUE;
    set[10] = TRUE;
    Trit t = set[10];
    ASSERT_EQ((Trit)set[0], TRUE);
    ASSERT_EQ((Trit)set[10], TRUE);
    ASSERT_EQ((Trit)set[100000], UNKNOWN);
    ASSERT_EQ(t, TRUE);

}

TEST(OperatorsTritSetTest, OperatorCompare) {
    TritSet set;
    set[0] = TRUE;
    set[10] = TRUE;
    ASSERT_EQ(set[10] == FALSE, 0);
    ASSERT_EQ(set[0] == TRUE, 1);
    ASSERT_EQ(set[10000] == UNKNOWN, 1);
}



TEST(MethodsTritSetTest, TrimSet) {
    TritSet set(100);
    for (size_t i = 0; i < 100; ++i)
        set[i] = Trit(TRUE);


    ASSERT_EQ(set.size(), 100);
    ASSERT_GE(set.capacity(), 100);

    set.trim(50);

    ASSERT_EQ(set.size(), 51);
    ASSERT_GE(set.capacity(), 51);

    for (size_t i = 0; i < 50; i++)
        ASSERT_EQ((Trit)set[i], TRUE);

    for (size_t i = 51; i < 100; i++)
        ASSERT_EQ((Trit)set[i], UNKNOWN);
}

TEST(MethodsTritSetTest, ShrinkSet) {
    TritSet set(1000);

    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 1000);

    set.shrink();
    
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);

    set[99] = TRUE;

    ASSERT_EQ(set.size(), 100);
    ASSERT_GE(set.capacity(), 100);

    set[49] = FALSE;

    ASSERT_EQ(set.size(), 100);
    ASSERT_GE(set.capacity(), 100);

    set[99] = UNKNOWN;

    ASSERT_EQ(set.size(), 50);
    ASSERT_GE(set.capacity(), 100);

    set.shrink();

    ASSERT_EQ(set.size(), 50);
    ASSERT_GE(set.capacity(), 50);
}

TEST(MethodsTritSetTest, Cardinality) {
    TritSet emptySet, setTrue(100, TRUE), setFalse(100, FALSE), setUnknown(100, UNKNOWN);

    ASSERT_EQ(emptySet.cardinality(TRUE), 0);
    ASSERT_EQ(emptySet.cardinality(FALSE), 0);
    ASSERT_EQ(emptySet.cardinality(UNKNOWN), 0);

    ASSERT_EQ(setTrue.cardinality(TRUE), 100);
    ASSERT_EQ(setTrue.cardinality(FALSE), 0);
    ASSERT_EQ(setTrue.cardinality(UNKNOWN), 0);

    ASSERT_EQ(setFalse.cardinality(TRUE), 0);
    ASSERT_EQ(setFalse.cardinality(FALSE), 100);
    ASSERT_EQ(setFalse.cardinality(UNKNOWN), 0);

    ASSERT_EQ(setUnknown.cardinality(TRUE), 0);
    ASSERT_EQ(setUnknown.cardinality(FALSE), 0);
    ASSERT_EQ(setUnknown.cardinality(UNKNOWN), 100);

    TritSet* set = tritSetFromString("FFFUUUTTT");
    std::unordered_map<Trit, size_t, std::hash<size_t>> map = {
            {TRUE,    3},
            {FALSE,   3},
            {UNKNOWN, 3},
    };

    ASSERT_EQ(set->cardinality(), map);
    delete set;
}

TEST(OperatorsTritSetTest, OperatorNOTEmpty) {
    TritSet emptySet;

    ASSERT_EQ(emptySet.size(), 0);
    ASSERT_GE(emptySet.capacity(), 0);

    emptySet = ~emptySet;

    ASSERT_EQ(emptySet.size(), 0);
    ASSERT_GE(emptySet.capacity(), 0);
}

TEST(OperatorsTritSetTest, OperatorNOTFalse) {
    TritSet setFalse(10, FALSE);

    ASSERT_EQ(setFalse.size(), 10);
    ASSERT_GE(setFalse.capacity(), 10);

    setFalse = ~setFalse;

    ASSERT_EQ(setFalse.size(), 10);
    ASSERT_GE(setFalse.capacity(), 10);

    for (size_t i = 0; i < 10; i++)
        ASSERT_EQ((Trit)setFalse[i], TRUE);
}

TEST(OperatorsTritSetTest, OperatorNOTUnknown) {
    TritSet setUnknown(10, UNKNOWN);

    ASSERT_EQ(setUnknown.size(), 0);
    ASSERT_GE(setUnknown.capacity(), 10);

    setUnknown = ~setUnknown;

    ASSERT_EQ(setUnknown.size(), 0);
    ASSERT_GE(setUnknown.capacity(), 0);

    for (size_t i = 0; i < 10; i++)
        ASSERT_EQ((Trit)setUnknown[i], UNKNOWN);
}

TEST(OperatorsTritSetTest, OperatorNOTTrue) {
    TritSet setTrue(10, TRUE);

    ASSERT_EQ(setTrue.size(), 10);
    ASSERT_GE(setTrue.capacity(), 10);

    setTrue = ~setTrue;

    ASSERT_EQ(setTrue.size(), 10);
    ASSERT_GE(setTrue.capacity(), 10);

    for (size_t i = 0; i < 10; i++)
        ASSERT_EQ((Trit)setTrue[i], FALSE);
}

TEST(OperatorsTritSetTest, OperatorNOTAll) {
    TritSet* setAll = tritSetFromString("FUTUTF");

    ASSERT_EQ(setAll->size(), 6);
    ASSERT_GE(setAll->capacity(), 6);

    *setAll = ~(*setAll);

    TritSet* notSet = tritSetFromString("TUFUFT");

    ASSERT_EQ((*setAll), (*notSet));

    delete setAll;
    delete notSet;
}

TEST(OperatorsTritSetTest, OperatorORContinually) {
    TritSet setEmpty;
    TritSet setTrue(10, TRUE), setFalse(10, FALSE), setUnknown(10, UNKNOWN);

    ASSERT_EQ(setEmpty.size(), 0);
    ASSERT_GE(setEmpty.capacity(), 0);

    ASSERT_EQ(setTrue.size(), 10);
    ASSERT_GE(setTrue.capacity(), 10);

    ASSERT_EQ(setFalse.size(), 10);
    ASSERT_GE(setFalse.capacity(), 10);

    ASSERT_EQ(setUnknown.size(), 0);
    ASSERT_GE(setUnknown.capacity(), 10);

    // Empty | Empty
    TritSet set = setEmpty | setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // True | Empty
    set = setTrue | setEmpty;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setTrue);

    // Unknown | Empty
    set = setUnknown | setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // False | Empty
    set = setFalse | setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // False | False
    set = setFalse | setFalse;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setFalse);

    // True | True
    set = setTrue | setTrue;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setTrue);

    // True | False
    set = setTrue | setFalse;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setTrue);

    // Unknown | Unknown
    set = setUnknown | setUnknown;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // Unknown | True
    set = setUnknown | setTrue;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setTrue);

    // Unknown | False
    set = setUnknown | setFalse;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);
}

TEST(OperatorsTritSetTest, OperatorORDifferent) {
    TritSet* setLeft = tritSetFromString("FUT");
    TritSet* setRight = tritSetFromString("TFU");
    TritSet* setLeftSmall = tritSetFromString("TU");
    TritSet* setRightSmall = tritSetFromString("UF");

    TritSet set = (*setLeft) | (*setRight);
    ASSERT_EQ(set.size(), 3);
    ASSERT_GE(set.capacity(), 3);
    TritSet* temp = tritSetFromString("TUT");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setLeft) | (*setLeftSmall);
    ASSERT_EQ(set.size(), 3);
    ASSERT_GE(set.capacity(), 3);
    temp = tritSetFromString("TUT");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setRight) | (*setRightSmall);
    ASSERT_EQ(set.size(), 2);
    ASSERT_GE(set.capacity(), 2);
    temp = tritSetFromString("TF");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setRight) | (*setLeftSmall);
    ASSERT_EQ(set.size(), 1);
    ASSERT_GE(set.capacity(), 1);
    temp = tritSetFromString("T");
    ASSERT_EQ(set, (*temp));
    delete temp;

    delete setLeft;
    delete setRight;
    delete setLeftSmall;
    delete setRightSmall;
}

TEST(OperatorsTritSetTest, OperatorANDContinually) {
    TritSet setEmpty;
    TritSet setTrue(10, TRUE), setFalse(10, FALSE), setUnknown(10, UNKNOWN);

    ASSERT_EQ(setEmpty.size(), 0);
    ASSERT_GE(setEmpty.capacity(), 0);

    ASSERT_EQ(setTrue.size(), 10);
    ASSERT_GE(setTrue.capacity(), 10);

    ASSERT_EQ(setFalse.size(), 10);
    ASSERT_GE(setFalse.capacity(), 10);

    ASSERT_EQ(setUnknown.size(), 0);
    ASSERT_GE(setUnknown.capacity(), 10);

    // Empty & Empty
    TritSet set = setEmpty & setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // True & Empty
    set = setTrue & setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // Unknown & Empty
    set = setUnknown & setEmpty;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // False & Empty
    set = setFalse & setEmpty;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setFalse);

    // False & False
    set = setFalse & setFalse;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setFalse);

    // True & True
    set = setTrue & setTrue;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setTrue);

    // True & False
    set = setTrue & setFalse;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setFalse);

    // Unknown & Unknown
    set = setUnknown & setUnknown;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // Unknown & True
    set = setUnknown & setTrue;
    ASSERT_EQ(set.size(), 0);
    ASSERT_GE(set.capacity(), 0);
    ASSERT_EQ(set, setEmpty);

    // Unknown & False
    set = setUnknown & setFalse;
    ASSERT_EQ(set.size(), 10);
    ASSERT_GE(set.capacity(), 10);
    ASSERT_EQ(set, setFalse);
}

TEST(OperatorsTritSetTest, OperatorANDDifferent) {
    TritSet* setLeft = tritSetFromString("FUT");
    TritSet* setRight = tritSetFromString("TFU");
    TritSet* setLeftSmall = tritSetFromString("TU");
    TritSet* setRightSmall = tritSetFromString("UF");

    TritSet set = (*setLeft) & (*setRight);
    ASSERT_EQ(set.size(), 2);
    ASSERT_GE(set.capacity(), 2);
    TritSet* temp = tritSetFromString("FF");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setLeft) & (*setLeftSmall);
    ASSERT_EQ(set.size(), 1);
    ASSERT_GE(set.capacity(), 1);
    temp = tritSetFromString("F");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setRight) & (*setRightSmall);
    ASSERT_EQ(set.size(), 2);
    ASSERT_GE(set.capacity(), 2);
    temp = tritSetFromString("UF");
    ASSERT_EQ(set, (*temp));
    delete temp;

    set = (*setRight) & (*setLeftSmall);
    ASSERT_EQ(set.size(), 2);
    ASSERT_GE(set.capacity(), 2);
    temp = tritSetFromString("TF");
    ASSERT_EQ(set, (*temp));
    delete temp;

    delete setLeft;
    delete setRight;
    delete setLeftSmall;
    delete setRightSmall;
}

TEST(OperatorsTritSetTest, OperatorEquals) {
    TritSet setTrue(10, TRUE), setFalse(10, FALSE), setUnknown(10, UNKNOWN);
    TritSet* setLeft = tritSetFromString("FUT");
    TritSet* setRight = tritSetFromString("UTF");
    TritSet* setSmall = tritSetFromString("TF");

    ASSERT_EQ(setTrue, setTrue);
    ASSERT_EQ(setFalse, setFalse);
    ASSERT_EQ(setUnknown, setUnknown);
    ASSERT_EQ((*setLeft), (*setLeft));
    ASSERT_EQ((*setSmall), (*setSmall));

    ASSERT_NE((*setLeft), (*setRight));
    ASSERT_NE(setTrue, setFalse);
    ASSERT_NE(setTrue, setUnknown);
    ASSERT_NE(setFalse, setUnknown);

    delete setLeft;
    delete setRight;
    delete setSmall;
}



TEST(OperatorsTritSetTest, OperatorSet) {
    TritSet set;

    ASSERT_EQ((set[0] = UNKNOWN).size(), 0);
    ASSERT_EQ(set.capacity(), 0);

    ASSERT_EQ((set[10] = UNKNOWN).size(), 0);
    ASSERT_EQ(set.capacity(), 0);

    ASSERT_EQ((set[0] = TRUE).size(), 1);
    ASSERT_GE(set.capacity(), 1);

    ASSERT_EQ((set[9] = TRUE).size(), 10);
    ASSERT_GE(set.capacity(), 10);
}