import { Text , StyleSheet , Platform } from 'react-native';
import Colors from '../../constants/colors';

function Tittle({children}){
    return <Text style={styles.tiitle}>{children}</Text>
}

export default Tittle;

const styles= StyleSheet.create({
    tiitle: {
            fontFamily: 'open-sans-bold',
            fontSize: 24,
           // fontWeight: 'bold',
            color: 'white',
            textAlign: 'center',
           // borderWidth: Platform.OS === 'android' ? 2 : 0,
          // borderWidth : Platform.select({ ios: 0, android: 2}),
          borderWidth: 2,
            borderColor: 'white',
            padding: 12,
            maxWidth: '80%',
            width: 300

    },
});