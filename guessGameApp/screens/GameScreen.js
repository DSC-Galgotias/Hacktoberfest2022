import { useState, useEffect } from 'react';
import { View, StyleSheet, Alert, FlatList, useWindowDimensions, } from 'react-native';
import { Ionicons } from '@expo/vector-icons';


import Colors from '../constants/colors';
import NumberContainer from '../components/game/NumberContainer';
import Tittle from '../components/ui/Tittle';
import PrimaryButton from '../components/ui/PrimaryButton';
import Card from '../components/ui/Card';
import InstructionText from '../components/ui/InstructionText';
import GuessLogItem from '../components/game/GuessLogItem';
function generateRandomBetween(min, max, exclude) {
    const rndNum = Math.floor(Math.random() * (max - min)) + min;

    if (rndNum === exclude) {
        return generateRandomBetween(min, max, exclude);
    } else {
        return rndNum;
    }
}

let minBoundary = 1;
let maxBoundary = 100;


function GameScreen({ userNumber, onGameOver }) {
    const initialGuess = generateRandomBetween(1, 100, userNumber);

    const [currentGuess, SetCurrentGuess] = useState(initialGuess);
    const [guessRounds, setGuessRounds] = useState([initialGuess]);
    const { width, height } = useWindowDimensions();
    useEffect(() => {
        if (currentGuess === userNumber) {
            onGameOver(guessRounds.length);
        }
    }, [currentGuess, userNumber, onGameOver]);



    useEffect(() => {
        minBoundary = 1;
        maxBoundary = 100;
    }, []);

    function nextGuessHandler(direction) {
        if ((direction === 'lower' && currentGuess < userNumber) ||
            (direction === 'greater' && currentGuess > userNumber)
        ) {

            Alert.alert("Don't lie!", 'You Know that this is wrong...',
                [
                    { text: 'Sorry!', style: 'cancel' },
                ]
            );
            return;
        }
        if (direction === 'lower') {
            maxBoundary = currentGuess;

        } else {
            minBoundary = currentGuess + 1;

        }
        console.log(minBoundary, maxBoundary);
        const newRndNumber = generateRandomBetween(minBoundary, maxBoundary, currentGuess);
        SetCurrentGuess(newRndNumber);
        setGuessRounds(prevGuessRounds => [newRndNumber, ...prevGuessRounds]);
    }

    const guessRoundsListLength = guessRounds.length;

    let content = (
        <>
            <NumberContainer>{currentGuess}</NumberContainer>
            <Card>
                <InstructionText style={styles.instructionText}>
                    Higher or lower?
                </InstructionText>
                {/* + - */}

                <View style={styles.buttonsContainer}>
                    <View style={styles.buttonContainer}>
                        <PrimaryButton onPress={nextGuessHandler.bind(this, 'lower')}>
                            <Ionicons name='md-remove' size={24} color="white" />
                        </PrimaryButton>

                    </View>
                    <View style={styles.buttonContainer}>
                        <PrimaryButton onPress={nextGuessHandler.bind(this, 'greater')}>
                            <Ionicons name='md-add' size={24} color="white" />
                        </PrimaryButton>
                    </View>
                </View>
            </Card>
        </>
    );

    if (width > 500) {
        content = (
            <>
               
                <View style={styles.buttonsContainerWide}>
                    <View style={styles.buttonContainer}>
                        <PrimaryButton onPress={nextGuessHandler.bind(this, 'lower')}>
                            <Ionicons name='md-remove' size={24} color="white" />
                        </PrimaryButton>

                    </View>
                    <NumberContainer>{currentGuess}</NumberContainer>
                    <View style={styles.buttonContainer}>
                        <PrimaryButton onPress={nextGuessHandler.bind(this, 'greater')}>
                            <Ionicons name='md-add' size={24} color="white" />
                        </PrimaryButton>
                    </View>


                </View>

            </>
        );
    }

    return (
        <View style={styles.screen}>
            <Tittle> Opponent's Guess</Tittle>
            {content}
            <View style={styles.listContainer}>
                {/*guessRounds.map(guessRounds => <Text key={guessRounds}>{guessRounds}</Text>)*/}
                <FlatList
                    data={guessRounds}
                    renderItem={(itemData) => <GuessLogItem roundNumber={guessRoundsListLength - itemData.index}
                        guess={itemData.item} />}
                    kewExtractor={(item) => item}
                />

            </View>
        </View>
    );
}

export default GameScreen;

const styles = StyleSheet.create({
    screen: {
        flex: 1,
        padding: 24,
        alignItems: 'center'
    },
    instructionText: {
        marginBottom: 12
    },
    buttonsContainer: {
        flexDirection: 'row',
    },
    buttonContainer: {
        flex: 1
    },
    buttonsContainerWide: {
        flexDirection: 'row',
        alignItems: 'center'
    },
    listContainer: {
        flex: 1,
        padding: 16
    }
})