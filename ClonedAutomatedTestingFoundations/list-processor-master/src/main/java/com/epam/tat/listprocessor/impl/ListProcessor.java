package com.epam.tat.listprocessor.impl;

import com.epam.tat.listprocessor.IListProcessor;
import com.epam.tat.listprocessor.exception.ListProcessorException;

import java.util.*;

import static java.util.Arrays.asList;

public class ListProcessor implements IListProcessor {

	@Override
	public String getSecondStringByLength(List<String> list) {
		if (list == null || list.size() <=1 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			Collections.sort(list, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.toString().compareTo(o2.toString());
				}
			});
			System.out.println(list);
			return list.get(list.size() - 2);
		}
	}

	@Override
	public List<String> getSortedListByLength(List<String> list) {
		if (list == null || list.size() <=1 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			int indexStringWithMinValue = 0;
			String maxLengthSting = "";
			String tempString = "";
			ArrayList<String> sortedList = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				if (maxLengthSting.length() < list.get(i).length()) {
					maxLengthSting = list.get(i);
				}
			}
			maxLengthSting = maxLengthSting + 'A';
			String minLengthString = maxLengthSting;


			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (minLengthString.length() > list.get(j).length()) {
						tempString = list.get(j);
						indexStringWithMinValue = j;
						minLengthString = list.get(j);
					}
				}
				list.set(indexStringWithMinValue, maxLengthSting);
				minLengthString = maxLengthSting;
				sortedList.add(tempString);
			}
			return sortedList;
		}

	}

	@Override
	public List<String> getSortedListByCountOfVowels(List<String> list) {
		if (list == null || list.size() <1 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			int countPresenceVowel =0;
			int numberOfVowelsChar;
			int maxNumberOfVowelsChar = 0;
			char ch;
			int[] matrixOfNumbersVowels = new int[list.size()];
			ArrayList<String> tempListForSorting = new ArrayList<>();
			ArrayList<String> sortedList = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				numberOfVowelsChar = 0;
				for (int j = 0; j < list.get(i).length(); j++) {
					ch = list.get(i).charAt(j);
					if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'y' ||
							ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' || ch == 'Y') {
						numberOfVowelsChar++;
					}

					if (numberOfVowelsChar > maxNumberOfVowelsChar)
						maxNumberOfVowelsChar = numberOfVowelsChar;

					matrixOfNumbersVowels[i] = numberOfVowelsChar;
				}
			}
			for (int i = 0; i <matrixOfNumbersVowels.length ; i++) {
				if (matrixOfNumbersVowels[i] > 0)
					countPresenceVowel++;
			}

			if (countPresenceVowel ==0) {
				throw new ListProcessorException("List incorrect");
			} else {
				for (int i = 0; i <= maxNumberOfVowelsChar; i++) {

					for (int j = 0; j < list.size(); j++) {
						if (matrixOfNumbersVowels[j] == i) {
							tempListForSorting.add(list.get(j));
						}
					}
					if (tempListForSorting.size() > 1) {
						Collections.sort(tempListForSorting, new Comparator<String>() {
							public int compare(String o1, String o2) {
								return o1.toString().compareTo(o2.toString());
							}
						});
					}

					for (int j = 0; j < tempListForSorting.size(); j++) {
						sortedList.add(tempListForSorting.get(j));
					}
					tempListForSorting.clear();
				}
				return sortedList;
			}
		}
	}

	@Override
	public List<String> getSortedListByCountOfConsonants(List<String> list) {
		if (list == null || list.size() < 1 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			int countPresenceConsonants=0;
			int consCount;
			int maxNumberOfConsonantLetters = 0;
			char ch;
			int[] matrixOfConsonantLetters = new int[list.size()];
			ArrayList<String> tempListForSorting = new ArrayList<>();
			ArrayList<String> sortedList = new ArrayList<>();

			for (int i = 0; i < list.size(); i++) {
				consCount = 0;
				for (int j = 0; j < list.get(i).length(); j++) {
					ch = list.get(i).charAt(j);
					if (Character.isLetter(ch) && (ch != 'a' && ch != 'e' && ch != 'i' && ch != 'o' && ch != 'u' && ch != 'y' &&
							ch != 'A' && ch != 'E' && ch != 'I' && ch != 'O' && ch != 'U' && ch != 'Y')) {
						consCount++;
					}

					if (consCount > maxNumberOfConsonantLetters)
						maxNumberOfConsonantLetters = consCount;

					matrixOfConsonantLetters[i] = consCount;
				}
			}

			for (int i = 0; i <matrixOfConsonantLetters.length ; i++) {
				if (matrixOfConsonantLetters[i] > 0)
					countPresenceConsonants++;
			}

			if (countPresenceConsonants ==0) {
				throw new ListProcessorException("List incorrect");
			} else {

				for (int i = 0; i <= maxNumberOfConsonantLetters; i++) {

					for (int j = 0; j < list.size(); j++) {
						if (matrixOfConsonantLetters[j] == i) {
							tempListForSorting.add(list.get(j));
						}
					}
					if (tempListForSorting.size() > 1) {
						Collections.sort(tempListForSorting, new Comparator<String>() {
							public int compare(String o1, String o2) {
								return o1.toString().compareTo(o2.toString());
							}
						});
					}

					for (int j = 0; j < tempListForSorting.size(); j++) {
						sortedList.add(tempListForSorting.get(j));
					}
					tempListForSorting.clear();
				}
				return sortedList;
			}
		}
	}

	@Override
	public List<String> changeByPlacesFirstAndLastSymbolsInEachSecondStringOfList(List<String> list) {
		if (list == null || list.size() < 2 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			char firstLetter;
			char lastLetter;
			String changedString;

			for (int i = 0; i < list.size(); i++) {
				if ((i + 1) % 2 == 0) {
					if (list.get(i).length() > 2) {
						firstLetter = list.get(i).charAt(0);
						lastLetter = list.get(i).charAt(list.get(i).length() - 1);
						String stringWithoutFirstAndLastLetter = list.get(i).substring(1, list.get(i).length() - 1);
						changedString = lastLetter + stringWithoutFirstAndLastLetter + firstLetter;
						list.set(i, changedString);
					}

				}

			}
			return list;
		}
	}

	@Override
	public List<String> reverseStringsOfList(List<String> list) {
		if (list == null || list.size() < 2 || (list.size()==2 && list.get(0)==list.get(1))
			||(list.size()==2 && (list.get(0).length()==1 || list.get(1).length()==1))) {
			throw new ListProcessorException("List incorrect");
		} else {
			String tempString;
			for (int i = 0; i < list.size(); i++) {
				tempString = "";
				for (int j = 0; j < list.get(i).length(); j++) {
					tempString = tempString + list.get(i).charAt(list.get(i).length() - j - 1);
				}
				list.set(i, tempString);
			}
			return list;
		}
	}
}
