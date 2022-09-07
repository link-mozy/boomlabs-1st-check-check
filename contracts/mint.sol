// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";
import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/utils/Counters.sol";

contract AttendanceCheck is ERC721, ERC721URIStorage {
    using Counters for Counters.Counter;

    Counters.Counter private _tokenIdCounter;

    constructor() ERC721("AttendanceCheck", "AC") {}

    function safeMint(address[] memory addressArray, string memory uri) public payable {
        for (uint256 i = 0; i < addressArray.length; i++) {
            uint256 tokenId = _tokenIdCounter.current();
            _safeMint(addressArray[i], tokenId);
            _setTokenURI(tokenId, uri);
            _tokenIdCounter.increment();
        }
    }

    function _burn(uint256 tokenId) internal override(ERC721, ERC721URIStorage) {
        super._burn(tokenId);
    }

    function tokenURI(uint256 tokenId)
        public
        view
        override(ERC721, ERC721URIStorage)
        returns (string memory)
    {
        return super.tokenURI(tokenId);
    }
}
