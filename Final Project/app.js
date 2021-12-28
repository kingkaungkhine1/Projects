/*
  Node based Spotify client api that uses the 'fetch' API from JS and uses promises   
*/
const e = require('express');
let fetch = require('node-fetch');
let readline = require('readline-sync');

// Global application variables
let client_id = '66a76c0265894abfa246248a3b30ce84'; // gained from spotify
let client_secret = '6e28f7905e0c420ba92e062473250a21'; // gained from spotify
let access_token; // Spotify access token needed to call REST API.  Good for 6 minutes.
/*
 getAccessToken - return a promise to get the access token response.
 Accounted for Spotify "client credentials" authorization.
*/
function getAccessToken()
{
    let buf = Buffer.from(client_id + ':' + client_secret).toString('base64');
    const formParams = new URLSearchParams({ grant_type: 'client_credentials' });
    return fetch('https://accounts.spotify.com/api/token',
        { method: 'POST', headers: { 'Authorization': 'Basic ' + buf }, body: formParams })
}
/*
 Assign the promise to the var tokenPromise1, it then wait for the response.
 Returns response.json() (another promise)
*/
let tokenPromise1 = getAccessToken()
    .then(response =>
    {
        if (!response.ok)
        {
            throw (new Error("Bad HTTP response code: " + response.status));
        }
        // this is a promise
        return response.json();  // can only call this 1x
    })
    .catch(err =>
    {
        console.log("Error: " + err);
    });
/*
 Assign the promise to another variable (tokenPromise2), then wait for it to resolve.
*/
let tokenPromise2 = tokenPromise1.then(json =>
{
    access_token = json.access_token;
});
/*
 searchArtist() - take in the artist name (queryVal) from the user and return a promise
 gets back an array of artists of that name
*/
function searchArtist(queryVal)
{
    queryVal = encodeURIComponent(queryVal);
    let uri = "https://api.spotify.com/v1/search?q=artist:" + queryVal + "&type=artist&limit=5";
    return fetch(uri,
        { method: 'GET', headers: { 'Authorization': 'Bearer ' + access_token } })
}
/*
 searchSong() - take in the song name (queryVal) from the user and return a promise
 gets back an array of songs with that name
*/
function searchSong(queryVal)
{
    queryVal = encodeURIComponent(queryVal);
    let uri = "https://api.spotify.com/v1/search?q=track:" + queryVal + "&type=track&limit=5";
    return fetch(uri,
        { method: 'GET', headers: { 'Authorization': 'Bearer ' + access_token } })
}
/*
 getArtistAlbums() - Accept the artist id (artistId) and return a promise
 to get an array of albums by the artist.
*/
function getArtistAlbums(artistId)
{
    let uri = "https://api.spotify.com/v1/artists/" + artistId + "/albums?market=US";
    //let uri = "https://api.spotify.com/v1/search?q=artist:"+ artistId + "/top-tracks?market=US";
    return fetch(uri,
        { method: 'GET', headers: { 'Authorization': 'Bearer ' + access_token } })
}
/*
 getAlbumTracks() - Accept the album id (albumId) and return a promise
 to get an array of tracks from the album.
*/
function getAlbumTracks(albumID)
{
    let uri = "https://api.spotify.com/v1/albums/" + albumID + "/tracks";
    return fetch(uri,
        { method: 'GET', headers: { 'Authorization': 'Bearer ' + access_token } })
}

/*
 displayArtists() - This async function displays the artist array (data) returned by searchArtist().
 From 0-4 the user should enter the correct artist name index. 
 This will then get and print out the artist's album history
 It issues additional Spotify requests and waits for them to return.
 called from main()
*/
async function displayArtists(data)
{
    data.artists.items.forEach((item, index) =>
    {
        console.log(`${index}. ${item.name}`);
    });
    let index = readline.question('Pick the # for the artist (or q to stop): ');
    if (index == 'q')
    {
        process.exit();
    }
    else if (index >= 0 && index <= 4)
    {
        let artistID = data.artists.items[index].id;
        let i = 0;
        try
        {
            let fetchPromise = getArtistAlbums(data.artists.items[index].id);
            response = await fetchPromise;
            if (!response.ok)
            {
                throw (new Error("Bad HTTP response code: " + response.status));
            }
            let responsePromise = response.json();
            let json = await responsePromise;
            // the while loop waits on the resolution of the promise returned here.
            return new Promise(function (resolve, reject)
            {
                displayAlbums(json, resolve, reject);
            })
        } catch (error)
        {
            console.log(error);
        }
    }
    else
    {
        console.log("Error please select a number between zero and four");
    }
}
/*
 displayTrack() - This async function displays the track array (data) returned by searchSong().
 From 0-4 the user should enter the song name index. 
 This will then get and print out the song's information with displayTrackInfo()
 called from main()
*/
async function displayTrack(data)
{
    data.tracks.items.forEach((item, index) =>
    {
        console.log(`${index}. ${item.name}`);
    });

    let index = readline.question('Pick the correct song (or q to stop): ');
    if (index == 'q')
    {
        process.exit();
    }
    if (index >= 0 && index <= 4)
    {
        try
        {
            let song = data.tracks.items[index];
            displayTrackInfo(song);
        }
        catch (error)
        {
            console.log(error);
        }

    }
    else
    {
        console.log("Error invalid input. Please select a number from 0-4 next time");
        return;
    }
}
/*
 displayAlbumTracks() - This async function displays the tracks from the artist's album array (data) returned by getAlbumTracks().
 From 0 to the track size of the album - 1 the user should enter the correct track index. 
 This will then get and print out the track's information with displayTrackInfoAlbum() (like name, what album it's from, release date, artist who worked on it)
 called from displayAlbums()
*/
async function displayAlbumTracks(data, albumName, albumReleaseDate)
{

    let tracksFromAlbum = data.items;

    for (let i = 0; i < tracksFromAlbum.length; i++)
    {
        console.log(i + ": " + tracksFromAlbum[i].name);
    }

    let trackIndex = readline.question('Pick the correct song (or q to stop): ');
    if (trackIndex == 'q')
    {
        process.exit();
    }
    else if (trackIndex >= 0 && trackIndex <= tracksFromAlbum.length - 1)
    {
        displayTrackInfoAlbum(tracksFromAlbum[trackIndex], albumName, albumReleaseDate);
    }
    else
    {
        let test = tracksFromAlbum.length - 1;
        console.log("Error please select a number between 0 and " + test);
        return;
    }
}

/*
 displayAlbums() - This async function displays the artist's album array (data) returned by displayArtists().
 From 0 to the total amount of artist albums - 1 the user should enter the correct album index. 
 This will then get and print out the tracks from the album with getAlbumTracks() and displayAlbumTracks()
 called from displayArtists()
*/
async function displayAlbums(data, resolve, reject)
{

    printAlbums(data);
    let albumIndex = readline.question('Select Album Index (or q to stop): ');
    let albumSize = data.items.length;
    if (albumIndex >= 0 && albumIndex <= data.items.length - 1)
    {
        while (albumIndex != 'q')
        {
            try
            {

                if (!(albumIndex >= 0 && albumIndex <= data.items.length - 1))
                {
                    let test = albumSize - 1;
                    console.log("Error please select a number between 0 and " + test);
                    break;
                }
                else
                {
                    let fetchPromise = getAlbumTracks(data.items[albumIndex].id);
                    let albumName = data.items[albumIndex].name;
                    let albumReleaseDate = data.items[albumIndex].release_date;
                    let response = await fetchPromise;
                    let responsePromise = response.json();
                    let json = await responsePromise;
                    loopPromise = displayAlbumTracks(json, albumName, albumReleaseDate);
                }

            } catch (error)
            {
                console.log(error);
            }
            await loopPromise;
            let pauseForAlbums = readline.question('Press enter to continue\n');
            printAlbums(data);
            albumIndex = readline.question('Enter Another Album Index (or q to stop): ');
            if (albumIndex == 'q')
            {
                process.exit();
            }
            else if (!(albumIndex >= 0 && albumIndex <= data.items.length - 1))
            {
                let test = albumSize - 1;
                console.log("Error please select a number between 0 and " + test);
                process.exit();
            }
        }
    }
    else
    {
        if (albumIndex == 'q')
        {
            process.exit();
        }
        let test = albumSize - 1;
        console.log("Error please select a number between 0 and " + test);
        return;
    }

    resolve(true);
}

//printAlbums() simply prints out all an artist's albums from getArtistAlbums()
//called from displayAlbums()
function printAlbums(data)
{
    for (let index = 0; index < data.items.length; index++)
        console.log(index + ": " + data.items[index].name);
}

//displayTrackInfo() simply prints out the song information from searchSong()
//called from displayTrack()
function displayTrackInfo(song)
{
    console.log("Song Name: " + song.name);
    console.log("Album: " + song.album.name);
    console.log("Release Date: " + song.album.release_date);

    if (song.album.artists.length >= 1)
    {
        for (let i = 0; i < song.album.artists.length; i++)
        {
            console.log("Artist: " + song.album.artists[i].name);
        }
    }
    else
    {
        console.log("Artist: " + song.album.artists[0].name);
    }
}
//displayTrackInfoAlbum() simply prints out the track from album information from getAlbumTracks()
//called from displayAlbumTracks()
//different from displayTrackInfo() as this method is used when the user types in an artist's name and not song name
function displayTrackInfoAlbum(albumSong, albumName, albumReleaseDate)
{
    console.log("Song Name: " + albumSong.name);
    console.log("Album: " + albumName);
    console.log("Release Date: " + albumReleaseDate);
    for (artistAlbumIndex = 0; artistAlbumIndex < albumSong.artists.length; artistAlbumIndex++)
    {
        console.log("Artist: " + albumSong.artists[artistAlbumIndex].name);
    }
}

/*
    main function of the application. Async so that it can get either the artist name or song name. 
    
    if the user types in 1, the program will go into artist name mode
    the program will prompt the user to type in an artist name
    the user will type in an artist name 
    the program gets the artist name, and then prints out options for the correct artist name
    the user will select the correct artist name
    from there the program will print the artist's albums
    the user can select the artist's albums
    the program will then print the album's tracklist
    the user can select a track from the album
    the program will then print information about the track (such as release date and artist name, and album)

    if the user types in 2, the program will go into song name mode
    the program will prompt the user to type in a song name
    the user will type in a song name
    the program will print out options for the correct song name
    the user will select the correct song title
    finally the program will then print out the song's information
    
    if the user types in 3, the program will terminate 

    while loop was taken from Prof Bob and modified to take either artist name or song. Top hits feature removed. 
    lots of use from "promise waits" I still don't really understand
*/
async function main()
{
    let strPick = readline.question('Press 1 for Artist, 2 for Song, 3 to end the program: ');
    while (strPick != '3')
    {
        if (strPick == '1')
        {
            await tokenPromise2;
            let loopPromise;
            let name = readline.question('Artist name (or q to stop): ');
            // There should be some client validation so that a input like " " does not get sent to the server as a search term. 
            if (name == "" || name == " ")
            {
                console.log("error please type in something for the artist name as an input");
                break;
            }
            while (name != 'q')
            {
                try
                {
                    let fetchPromise = searchArtist(name);
                    let response = await fetchPromise;
                    let responsePromise = response.json();
                    let json = await responsePromise;
                    loopPromise = displayArtists(json);
                } catch (error)
                {
                    console.log(error);
                }
                await loopPromise;
                name = readline.question('Another artist name (or q to stop): ');
                if (name == 'q')
                {
                    process.exit();
                }
            }
            if (name == 'q')
            {
                process.exit();
            }
        }
        else if (strPick == '2')
        {
            await tokenPromise2;
            let loopPromise;
            let name = readline.question('Song name (or q to stop): ');
            // There should be some client validation so that a input like " " does not get sent to the server as a search term. 
            if (name == "" || name == " ")
            {
                console.log("error please type in something for the song name as an input");
                break;
            }
            while (name != 'q')
            {
                try
                {
                    let fetchPromise = searchSong(name);
                    let response = await fetchPromise;
                    let responsePromise = response.json();
                    let json = await responsePromise;
                    loopPromise = displayTrack(json);
                } catch (error)
                {
                    console.log(error);
                }
                await loopPromise;
                name = readline.question('Another song name (or q to stop): ');
                if (name == 'q')
                {
                    process.exit();
                }
            }
            if (name == 'q')
            {
                process.exit();
            }
        }
        else if (strPick == '3')
        {
            break;
        }
        else
        {
            console.log("Error: Input is not correct")
            break;
        }

    }

}

// main code - a synchronous loop
main();